package com.fbla.quickchef.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fbla.quickchef.forms.FormStep1;
import com.fbla.quickchef.forms.FormStep3;
import com.fbla.quickchef.forms.FormStep2;
import com.fbla.quickchef.model.Address;
import com.fbla.quickchef.model.Order;
import com.fbla.quickchef.model.Role;
import com.fbla.quickchef.model.User;
import com.fbla.quickchef.model.UserPaymentInfo;
import com.fbla.quickchef.model.UserPreference;
import com.fbla.quickchef.repositories.OrderRepo;
import com.fbla.quickchef.repositories.RoleRepo;
import com.fbla.quickchef.repositories.UserPaymentInfoRepo;
import com.fbla.quickchef.repositories.UserPreferenceRepo;
import com.fbla.quickchef.repositories.UserRepo;
import com.fbla.quickchef.utils.BeanUtilsExt;

@Controller
public class LoginController {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserPreferenceRepo userPreferenceRepo;
	
	@Autowired
	private UserPaymentInfoRepo userPaymentInfoRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("activeTab", "login");
		return modelAndView;
    }
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public ModelAndView signup(FormStep1 form) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signup");
		modelAndView.addObject("formStep1", new FormStep1());
		modelAndView.addObject("currentStep", "step1");
		modelAndView.addObject("formAction", "/signupStep1");
		modelAndView.addObject("hideLoginDetail", false);
		return modelAndView;
	}
	
	@RequestMapping(value="/signupStep1", method=RequestMethod.POST)
	public ModelAndView step1Registration(@Valid FormStep1 form, BindingResult result, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentStep", "step1");
		
		// Check to make sure both username and password are provided
		// Didn't add validation annotation since the same form are used for both registration and checkout process
		if(StringUtils.isNullOrEmpty(form.getUsername()) || StringUtils.isNullOrEmpty(form.getPassword())) {
			if(StringUtils.isNullOrEmpty(form.getUsername())) {
				result.rejectValue("username", null, "Username is a required field");
			}
			
			if(StringUtils.isNullOrEmpty(form.getPassword())) {
				result.rejectValue("password", null, "Password is a required field");
			}
		} else if(userRepo.exists(form.getUsername())) {
			// make sure user doesn't exists in DB
			User user = userRepo.findOne(form.getUsername());
			if(user != null && user.getActive() == 1) {
				result.reject("global.error.user.exists", 
					new Object[] { form.getUsername() }, 
						"User with username {0} already exists. Please try another username.");
			}
		}
		
		if(result.hasErrors()) {
			modelAndView.addAllObjects(result.getModel());
			modelAndView.setViewName("signup");
			modelAndView.addObject("hideLoginDetail", false);
			return modelAndView;
		}
		
		Role role = roleRepo.findByRole("MEMBER");
		if(role != null) {
			User user = BeanUtilsExt.copy(new User(), form);
			Address address = BeanUtilsExt.copy(new Address(), form);
			
			user.setActive(0);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.getAddress().add(address);
			user.getRoles().add(role);
			user = userRepo.save(user);
			
			session.setAttribute("user", user);
		}
		
		if(!modelAndView.getModel().containsKey("formStep3")) {
			modelAndView.addObject("formStep2", new FormStep2());
		}
		
		modelAndView.addObject("currentStep", "step2");
		modelAndView.addObject("formAction", "/signupStep2");
		modelAndView.setViewName("signup");
		return modelAndView;
	}
	
	@RequestMapping(value="/signupStep2", method=RequestMethod.POST)
	public ModelAndView step2Registration(@Valid FormStep2 form, BindingResult result, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentStep", "step2");
		
		if(result.hasErrors()) {
			modelAndView.addAllObjects(result.getModel());
			modelAndView.setViewName("signup");
			return modelAndView;
		}
		
		User user = (User) session.getAttribute("user");
		if(user != null) {
			UserPreference preference = BeanUtilsExt.copy(new UserPreference(), form);
			preference.setUsername(user.getUsername());
			preference = userPreferenceRepo.save(preference);
			
			session.setAttribute("preference", preference);
		}
		
		if(!modelAndView.getModel().containsKey("formStep3")) {
			modelAndView.addObject("formStep3", new FormStep3());
		}
		
		modelAndView.addObject("currentStep", "step3");
		modelAndView.addObject("formAction", "/signupStep3");
		modelAndView.setViewName("signup");
		return modelAndView;
	}
	
	@RequestMapping(value="/signupStep3", method=RequestMethod.POST)
	public ModelAndView step3Registration(@Valid FormStep3 form, BindingResult result, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentStep", "step3");
		
		if(result.hasErrors()) {
			modelAndView.addAllObjects(result.getModel());
			modelAndView.setViewName("signup");
			return modelAndView;
		}
		
		User user = (User) session.getAttribute("user");
		if(user != null) {
			UserPaymentInfo paymentInfo = BeanUtilsExt.copy(new UserPaymentInfo(), form);
			paymentInfo.setUsername(user.getUsername());
			userPaymentInfoRepo.save(paymentInfo);
			
			UserPreference preference = (UserPreference) session.getAttribute("preference");
			if(preference == null) {
				preference = userPreferenceRepo.findOne(user.getUsername());
				if(preference == null) {
					preference = new UserPreference();
				}
			}
			
			modelAndView.addObject("preference", preference);
			
			Order order = BeanUtilsExt.copy(new Order(), preference);
			order.setUsername(user.getUsername());
			order.setOrderDate(new Date());
			order = orderRepo.save(order);
				
			modelAndView.addObject("order", order);
		}
		
		modelAndView.addObject("currentStep", "step4");
		modelAndView.addObject("formAction", "/signupStep4");
		modelAndView.setViewName("signup");
		return modelAndView;
	}
	
	@RequestMapping(value="/signupStep4", method=RequestMethod.POST)
	public ModelAndView step4Registration(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		User user = (User) session.getAttribute("user");
		UserPreference preference = (UserPreference) session.getAttribute("preference");
		
		Order order = (Order) session.getAttribute("order");
		if(order == null && user != null && preference != null) {
			order = new Order();
			order.setUsername(user.getUsername());
			order.setOrderDate(new Date());
			order = orderRepo.save(order);
		}
			
		if(user != null) {
			user.setActive(1);
			userRepo.save(user);
		}
		
		modelAndView.setViewName("login");
		modelAndView.addObject("activeTab", "login");
		return modelAndView;
	}
}
