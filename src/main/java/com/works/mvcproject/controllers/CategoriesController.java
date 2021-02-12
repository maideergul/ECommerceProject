package com.works.mvcproject.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.works.mvcproject.models.Category;
import com.works.mvcproject.repositories.CategoryRepository;
import com.works.mvcproject.repositories.ProductRepository;

@Controller
public class CategoriesController {
	
	final CategoryRepository categoryRepo;
	final ProductRepository productRepo;
	
	public CategoriesController( CategoryRepository categoryRepo, ProductRepository productRepo) {
		this.categoryRepo = categoryRepo;
		this.productRepo = productRepo;
	}	
	
	@GetMapping("/categories")
	public String services(Model model)
	{
		model.addAttribute("productList", categoryRepo.findAll());
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("cartCount", productRepo.countByStatu(1).size());
	
		return "categories";
	}
	
	int spid=0;
	
	String page = "";
	@GetMapping("/category/{cid}")
	public String detailProduct(@PathVariable String cid, Model model)
	{		
		try {
			spid  = Integer.parseInt(cid);
			Optional<Category> ocategory = categoryRepo.findById(spid);
			ocategory.ifPresent(item -> { 
				model.addAttribute("detail", item);
				model.addAttribute("productList", productRepo.findByCid(spid));
				model.addAttribute("categoryList", categoryRepo.findAll());
				model.addAttribute("cartCount", productRepo.countByStatu(1).size());
				page = "product";
			});
			
			if(!ocategory.isPresent()) 
			{
				page = "redirect:/single";
				return page;
			}
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}
	

	
}