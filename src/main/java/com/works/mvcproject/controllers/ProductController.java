package com.works.mvcproject.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.works.mvcproject.models.Product;
import com.works.mvcproject.repositories.CategoryRepository;
import com.works.mvcproject.repositories.ProductRepository;

@Controller
public class ProductController {
	
	final ProductRepository productRepo;
	final CategoryRepository categoryRepo;
	
	public ProductController(ProductRepository productRepo,CategoryRepository categoryRepo) {
		this.productRepo = productRepo;
		this.categoryRepo=categoryRepo;
	}
	
	int spid=0;

	@GetMapping("/product")
	public String product(Model model)
	{			
		model.addAttribute("productList", productRepo.findAll());	
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("cartCount", productRepo.countByStatu(1).size());		
		
		return "product";
	}
	
	String page = "";
	@GetMapping("/detail/{pid}")
	public String detailProduct(@PathVariable String pid, Model model)
	{		
		try {
			spid  = Integer.parseInt(pid);
			Optional<Product> oproduct = productRepo.findById(spid);
			oproduct.ifPresent(item -> { 
				model.addAttribute("detail", item);
				model.addAttribute("productList", productRepo.findAll());
				model.addAttribute("categoryList", categoryRepo.findAll());
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
	
	@GetMapping("/carts/{pid}")
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
				page = "redirect:/product";
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