package com.fbla.quickchef.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fbla.quickchef.forms.CoverageForm;
import com.fbla.quickchef.model.Coverage;
import com.fbla.quickchef.model.Recipe;
import com.fbla.quickchef.repositories.CoverageRepo;
import com.fbla.quickchef.repositories.RecipeRepo;

@Controller
public class SiteController {
	@Autowired
	private RecipeRepo recipeRepo; 
	
	@Autowired
	private CoverageRepo coverageRepo;
	
	@RequestMapping("/howItWorks")
	public String gotoHowItWorksPage() {
		return "howItWorks";
	}
	
	@RequestMapping("/menu")
	public ModelAndView gotoMenuPage() {
		List<Recipe> recipes = recipeRepo.findAll();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("recipes", recipes);
		modelAndView.setViewName("menu");
		return modelAndView;
	}
	
	@RequestMapping("/checkCoverage")
	public ModelAndView checkCoverage(CoverageForm form) {
		ModelAndView model = new ModelAndView();
		model.setViewName("coverageResult");
		
		Coverage coverage = coverageRepo.findOne(form.getZip());
		if(coverage != null) {
			model.addObject("isCover", true);
		} else {
			model.addObject("isCover", false);
		}
		
		return model;
	}
}
