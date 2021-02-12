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
import com.works.mvcproject.models.Product;
import com.works.mvcproject.repositories.CategoryRepository;
import com.works.mvcproject.repositories.ProductCidViewRepository;
import com.works.mvcproject.repositories.ProductRepository;

@Controller
public class ProductEditController {
	
final ProductRepository productRepo;
final CategoryRepository categoryRepo;
final ProductCidViewRepository pcvRepo;
	
	public ProductEditController(ProductRepository productRepo,CategoryRepository categoryRepo, ProductCidViewRepository pcvRepo) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
		this.pcvRepo=pcvRepo;
	}
	
	int pid = 0;

	@GetMapping("/productEdit")
	public String productEdit(Model model)
	{		
		pid = 0;
		model.addAttribute("pcvlist", pcvRepo.findAll());		
		model.addAttribute("categoryList",categoryRepo.findAll());
				
		return "productEdit";
	}
	
	@PostMapping("/addProduct")
	public String productAdd(Product product,Category category)
	{
		if(pid != 0)
		{
			product.setPid(pid);			
		}					
		productRepo.saveAndFlush(product);
		pid = 0;		
		
		return "redirect:/productEdit";		
	}
	
	@GetMapping("/deleteProduct/{spid}")
	public String productDelete(@PathVariable String spid)
	{
		int pid = 0;
		try {
			pid  = Integer.parseInt(spid);
			productRepo.deleteById(pid);
		} catch (NumberFormatException e) {
			return "redirect:/";
		}catch (EmptyResultDataAccessException e) {
			return "redirect:/productEdit";
		}
		
		return "redirect:/productEdit";
	}	
	
	String page = "";
	@GetMapping("/updateProduct/{spid}")
	public String updateProduct(@PathVariable String spid, Model model)
	{		
		try {
			pid  = Integer.parseInt(spid);
			Optional<Product> oproduct = productRepo.findById(pid);
			oproduct.ifPresent(item -> { 
				model.addAttribute("update", item);
				model.addAttribute("productList", productRepo.findAll());
				model.addAttribute("categoryList", categoryRepo.findAll());
				page = "productEdit";
			});
			
			if(!oproduct.isPresent()) 
			{
				page = "redirect:/productEdit";
				return page;
			}
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}
	
	@PostMapping("/searchData")
	public String searchData(@RequestParam String searchTxt, Model model)
	{
		if(searchTxt.equals(""))
		{
			return "redirect:/productEdit";
		}
		
		model.addAttribute("searchTxt", searchTxt);
		model.addAttribute("pcvlist", pcvRepo.searchData("%"+searchTxt+"%"));		
		return "productEdit";
	}	

}