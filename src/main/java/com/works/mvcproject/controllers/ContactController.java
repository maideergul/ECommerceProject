package com.works.mvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.works.mvcproject.repositories.CategoryRepository;
import com.works.mvcproject.repositories.ProductRepository;

@Controller
public class ContactController {
	
	final CategoryRepository categoryRepo;
	final ProductRepository productRepo;
	
	public ContactController( CategoryRepository categoryRepo,ProductRepository productRepo) {
		this.categoryRepo=categoryRepo;
		this.productRepo=productRepo;
	}

	@GetMapping("/contact")
	public String contact(Model model)
	{
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("cartCount", productRepo.countByStatu(1).size());
		return "contact";
	}
	
}