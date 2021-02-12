package com.works.mvcproject.controllers;

import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.works.mvcproject.models.Category;
import com.works.mvcproject.repositories.CategoryRepository;

@Controller
public class CategoryEditController {
	
	final CategoryRepository categoryRepo;
	
	public CategoryEditController(CategoryRepository categoryRepo) {
		this.categoryRepo=categoryRepo;
	}
	
	int cid = 0;

	@GetMapping("/categoryEdit")
	public String categoryEdit(Model model)
	{		
		cid = 0;
		model.addAttribute("categoryList", categoryRepo.findAll());
				
		return "categoryEdit";
	}
	
	@PostMapping("/addCategory")
	public String categoryAdd(Category category)
	{
		if(cid != 0)
		{
			category.setCid(cid);
		}
			
		categoryRepo.saveAndFlush(category);
		cid = 0;	
		
		return "redirect:/categoryEdit";
	}
	
	@GetMapping("/deleteCategory/{scid}")
	public String productDelete(@PathVariable String scid)
	{
		int cid = 0;
		try {
			cid  = Integer.parseInt(scid);
			categoryRepo.deleteById(cid);
		} catch (NumberFormatException e) {
			return "redirect:/";
		}catch (EmptyResultDataAccessException e) {
			return "redirect:/categoryEdit";
		}
		
		return "redirect:/categoryEdit";
	}	
	
	String page = "";
	@GetMapping("/updateCategory/{scid}")
	public String updateCategory(@PathVariable String scid, Model model)
	{		
		try {
			cid  = Integer.parseInt(scid);
			Optional<Category> ocategory = categoryRepo.findById(cid); 
			ocategory.ifPresent(item -> { 
				model.addAttribute("update", item);
				model.addAttribute("categoryList", categoryRepo.findAll());
				page = "categoryEdit";
			});
			
			if(!ocategory.isPresent()) 
			{
				page = "redirect:/categoryEdit";
				return page;
			}
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}
	
	@PostMapping("/searchCategory")
	public String searchCategory(@RequestParam String searchTxt, Model model)
	{
		if(searchTxt.equals(""))
		{
			return "redirect:/categoryEdit";
		}
		
		model.addAttribute("searchTxt", searchTxt);
		model.addAttribute("categoryList", categoryRepo.searchData("%"+searchTxt+"%"));
		
		return "categoryEdit";
	}	

}