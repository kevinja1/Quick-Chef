package com.fbla.quickchef.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fbla.quickchef.forms.PreferenceForm;
import com.fbla.quickchef.model.User;
import com.fbla.quickchef.model.UserPaymentInfo;
import com.fbla.quickchef.model.UserPreference;
import com.fbla.quickchef.repositories.OrderRepo;
import com.fbla.quickchef.repositories.UserPaymentInfoRepo;
import com.fbla.quickchef.repositories.UserPreferenceRepo;
import com.fbla.quickchef.repositories.UserRepo;
import com.fbla.quickchef.utils.BeanUtilsExt;

/**
 * This is the member area controller class and only received request when user are authenticated
 */
@Controller
public class MemberController {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserPreferenceRepo userPreferenceRepo;
	
	@Autowired
	private UserPaymentInfoRepo userPaymentInfoRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@RequestMapping("/member/account")
	public ModelAndView gotoAccount(HttpSession session, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		
		User user = userRepo.getOne(principal.getName());
		UserPreference preference = userPreferenceRepo.getOne(principal.getName());
		UserPaymentInfo paymentInfo = userPaymentInfoRepo.getOne(principal.getName());
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("preference", preference);
		modelAndView.addObject("paymentInfo", paymentInfo);
		modelAndView.setViewName("account");
		
		return modelAndView;
	}
	
	@RequestMapping("/member/preferenceUpdate")
	public ModelAndView updatePreference(PreferenceForm form, ModelAndView modelAndView, HttpSession session, Principal principal) {
		UserPreference preference = userPreferenceRepo.getOne(principal.getName());
		preference = BeanUtilsExt.copy(preference,form);
		preference = userPreferenceRepo.save(preference);
		
		User user = userRepo.getOne(principal.getName());
		UserPaymentInfo paymentInfo = userPaymentInfoRepo.getOne(principal.getName());
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("preference", preference);
		modelAndView.addObject("paymentInfo", paymentInfo);
		modelAndView.setViewName("account");
		
		return modelAndView;
	}
}
