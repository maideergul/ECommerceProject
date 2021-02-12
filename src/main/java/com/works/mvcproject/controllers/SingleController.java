package com.works.mvcproject.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.works.mvcproject.models.Product;
import com.works.mvcproject.repositories.CategoryRepository;
import com.works.mvcproject.repositories.ProductRepository;

@Controller
public class SingleController {
	
	final ProductRepository productRepo;
	final CategoryRepository categoryRepo;	
	
	public SingleController(CategoryRepository categoryRepo,ProductRepository productRepo) {
		this.categoryRepo=categoryRepo;
		this.productRepo=productRepo;
	}
	
	int spid = 0;

	@GetMapping("/single")
	public String single(Model model)
	{
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("productList", productRepo.findAll());
		return "single";
	}	
	
	String page = "";
	@PostMapping("/detailProduct/{pid}")
	public String detailProduct(@PathVariable String pid, Model model)
	{		
		try {			
			spid  = Integer.parseInt(pid);
			Optional<Product> oproduct = productRepo.findById(spid);
			oproduct.ifPresent(item -> { // not null
				model.addAttribute("detail", item);
				model.addAttribute("productList", productRepo.findAll());
				model.addAttribute("categoryList", categoryRepo.findAll());
				page = "single";
			});
			
			if(!oproduct.isPresent()) //null
			{
				page = "redirect:/single";
				return page;
			}
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}		

	@GetMapping("/cart/{pid}")
	public String cart(@PathVariable String pid, Model model)
	{		
		try {
			spid  = Integer.parseInt(pid);
			Optional<Product> oproduct = productRepo.findById(spid);
			oproduct.ifPresent(item -> { 
				
				if(item.getPstatu() == 0)
				{		
					item.setPstatu(1);
					item.setQuantity(1);					
					productRepo.saveAndFlush(item);
				}else
				{
					item.setQuantity(item.getQuantity()+1);
					productRepo.saveAndFlush(item);
				}
				
				model.addAttribute("detail", item);
				model.addAttribute("productList", productRepo.findAll());
				model.addAttribute("categoryList", categoryRepo.findAll());
				model.addAttribute("cartCount", productRepo.countByStatu(1).size());
				page = "single";
			});
			
			if(!oproduct.isPresent()) 
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