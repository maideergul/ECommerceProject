package com.works.mvcproject.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.works.mvcproject.models.Product;
import com.works.mvcproject.repositories.CategoryRepository;
import com.works.mvcproject.repositories.ProductCidViewRepository;
import com.works.mvcproject.repositories.ProductRepository;

@Controller
public class IndexController {
	
	final ProductRepository productRepo;
	final CategoryRepository categoryRepo;
	final ProductCidViewRepository pcvRepo;
	
	public IndexController(ProductRepository productRepo,CategoryRepository categoryRepo,ProductCidViewRepository pcvRepo) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
		this.pcvRepo=pcvRepo;
	}

	int spid=0;
	
	@GetMapping("/index")
	public String index(Model model)
	{
		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("cartCount", productRepo.countByStatu(1).size());
		return "index";
	}
	
	String page = "";
	@GetMapping("/detailProduct/{pid}")
	public String detailProduct(@PathVariable String pid, Model model)
	{		
		try {
			spid  = Integer.parseInt(pid);
			Optional<Product> oproduct = productRepo.findById(spid);
			oproduct.ifPresent(item -> { 
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
	
	@GetMapping("/addToCart/{pid}")
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
				page = "redirect:/index";
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
	
	@PostMapping("/search")
	public String search(@RequestParam String searchTxt, Model model)
	{
		if(searchTxt.equals(""))
		{
			return "redirect:/index";
		}
		
		model.addAttribute("searchTxt", searchTxt);
		model.addAttribute("categoryList", categoryRepo.findAll());		
		model.addAttribute("cartCount", productRepo.countByStatu(1).size());
		model.addAttribute("productList", pcvRepo.searchData("%"+searchTxt+"%"));
		return "product";
	}	
	
}