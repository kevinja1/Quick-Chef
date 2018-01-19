package com.fbla.quickchef.controllers;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fbla.quickchef.cart.CartItem;
import com.fbla.quickchef.cart.ShoppingCart;
import com.fbla.quickchef.forms.CheckoutForm;
import com.fbla.quickchef.forms.FormStep1;
import com.fbla.quickchef.forms.FormStep3;
import com.fbla.quickchef.model.Address;
import com.fbla.quickchef.model.Recipe;
import com.fbla.quickchef.model.User;
import com.fbla.quickchef.model.UserPaymentInfo;
import com.fbla.quickchef.repositories.OrderRepo;
import com.fbla.quickchef.repositories.RecipeRepo;
import com.fbla.quickchef.repositories.UserPaymentInfoRepo;
import com.fbla.quickchef.repositories.UserRepo;
import com.fbla.quickchef.utils.BeanUtilsExt;

/**
 * This is the controller class for handling all shopping
 * cart request.
 */
@Controller
public class CartController {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserPaymentInfoRepo userPaymentInfoRepo;
	
	@Autowired
	private RecipeRepo recipeRepo;
	
	@RequestMapping("/addToCart")
	public ModelAndView addToCart(Long recipeId, HttpSession session) {
		// locate a recipe record from DB using the recipeId
		Recipe recipe = recipeRepo.getOne(recipeId);
		
		if(recipe != null) {
			// if recipe is found update the cart with the submitted recipe item.
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
			if(cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("cart",cart);
			}
			
			// check to see if item already exist in the cart
			CartItem item = cart.getCartItems().get(recipeId);
			if(item == null) {
				// item not found so create a new entry in the cart.
				item = new CartItem();
				item.setName(recipe.getName());
				item.setRecipeId(recipe.getRecipeId());
			} else {
				// existing item found so just need to update the quantity
				item.incrementQuantity();
			}
			
			// update cart with the new updated item
			cart.getCartItems().put(recipeId, item);
		}
		
		// retrieve all recipe from DB
		List<Recipe> recipes = recipeRepo.findAll();
		
		ModelAndView model = new ModelAndView();
		model.addObject("recipes", recipes);
		model.setViewName("menu"); // menu is the view name in Spring MVC which resolved to menu.html when request is returned
		return model;
	}
	
	@RequestMapping("/gotoCart")
	public ModelAndView gotoCart(HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		List<CartItem> items = Collections.emptyList();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if(cart != null && !CollectionUtils.isEmpty(cart.getAllItems())) {
			items = cart.getAllItems();
		}
		
		// CheckoutForm represent the form used on the shopping cart
		CheckoutForm form = new CheckoutForm();
		form.setItems(items);
		
		model.addObject("checkoutForm", form);
		model.setViewName("cart");
		return model;
	}
	
	@RequestMapping("/updateCart")
	public ModelAndView gotoCart(CheckoutForm form, HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if(cart != null && !CollectionUtils.isEmpty(cart.getAllItems())) {
			// update cart
			for(CartItem item : form.getItems()) {
				if(item.getQuantity() == 0) {
					cart.getCartItems().remove(item.getRecipeId());
				} else {
					cart.getCartItems().put(item.getRecipeId(), item);
				}
			}
		} else {
			cart = new ShoppingCart();
		}
		
		form.setItems(cart.getAllItems());
		model.addObject("checkoutForm", form);
		model.setViewName("cart");
		return model;
	}
	
	@RequestMapping("/checkout")
	public ModelAndView checkout(HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("formAction", "/checkoutStep1");
		model.addObject("formStep1", new FormStep1());
		model.addObject("currentStep", "step1");
		model.addObject("hideLoginDetail", true);
		model.setViewName("checkout");
		
		return model;
	}
	
	/**
	 * Form wizard step 1 request handler. 
	 * This method handles the request for step 1 when user checking out the item from the shopping cart.
	 * It does form validation to make sue all required fields are entered.
	 * It also prepare model data for the next step in the wizard.
	 */
	@RequestMapping(value="/checkoutStep1", method=RequestMethod.POST)
	public ModelAndView step1Checkout(@Valid FormStep1 form, BindingResult result, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentStep", "step1");
		
		User user = null;
		
		// Check to see if this customer is a repeat customer
		if(userRepo.exists(form.getEmail())) {
			user = userRepo.findOne(form.getEmail());
		} else {
			user = BeanUtilsExt.copy(new User(),form);
			Address address = BeanUtilsExt.copy(new Address(), form);
			
			user.setUsername(form.getEmail());
			user.getAddress().add(address);
			
			user = userRepo.save(user);
			session.setAttribute("user", user);
		}
		
		if(result.hasErrors()) {
			modelAndView.addAllObjects(result.getModel());
			modelAndView.setViewName("checkout");
			return modelAndView;
		}
		
		if(!modelAndView.getModel().containsKey("formStep3")) {
			modelAndView.addObject("formStep3", new FormStep3());
		}
		
		modelAndView.addObject("currentStep", "step3");
		modelAndView.addObject("formAction", "/checkoutStep2");
		modelAndView.setViewName("checkout");
		return modelAndView;
	}
	
	/**
	 * Form wizard step 2 request handler. 
	 * This method handles the request for step 2 when user checking out the item from the shopping cart.
	 * It does form validation to make sue all required fields are entered.
	 * It also prepare model data for the next step in the wizard.
	 */
	@RequestMapping(value="/checkoutStep2", method=RequestMethod.POST)
	public ModelAndView step2Checkout(@Valid FormStep3 form, BindingResult result, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentStep", "step3");
		
		User user = (User) session.getAttribute("user");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("cart", cart);
		
		if(result.hasErrors()) {
			modelAndView.addAllObjects(result.getModel());
			modelAndView.setViewName("checkout");
			return modelAndView;
		}
		
		if(user != null) {
			UserPaymentInfo paymentInfo = BeanUtilsExt.copy(new UserPaymentInfo(), form);
			paymentInfo.setUsername(user.getUsername());
			userPaymentInfoRepo.save(paymentInfo);
			modelAndView.addObject("paymentInfo", paymentInfo);
			session.setAttribute("paymentInfo", paymentInfo);
		}
		
		modelAndView.addObject("currentStep", "checkoutSummary");
		modelAndView.addObject("formAction", "/checkoutSummary");
		modelAndView.addObject("hideButton", false);
		modelAndView.setViewName("checkout");
		return modelAndView;
	}
	
	/**
	 * Form wizard step 3 request handler. 
	 * This method receive the confirmation of the order from users and clear out the 
	 * shopping cart.
	 */
	@RequestMapping(value="/checkoutSummary", method=RequestMethod.POST)
	public ModelAndView completeOrder(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		UserPaymentInfo paymentInfo = (UserPaymentInfo) session.getAttribute("paymentInfo");
		
		session.removeAttribute("cart");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cart", cart);
		modelAndView.addObject("paymentInfo", paymentInfo);
		modelAndView.addObject("currentStep", "checkoutSummary");
		modelAndView.addObject("formAction", "/checkoutSummary");
		modelAndView.addObject("message", "Order submitted");
		modelAndView.addObject("hideButton", true);
		modelAndView.setViewName("checkout");
		return modelAndView;
	}
}
