package com.works.mvcproject.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.works.mvcproject.models.Product;
import com.works.mvcproject.repositories.CartProcRepository;
import com.works.mvcproject.repositories.CategoryRepository;
import com.works.mvcproject.repositories.ProductRepository;

@Controller
public class CartController {
	
	final CategoryRepository categoryRepo;
	final ProductRepository productRepo;
	final CartProcRepository cartRepo;	
	
	public CartController(CategoryRepository categoryRepo, ProductRepository productRepo,CartProcRepository cartRepo) {
		this.categoryRepo = categoryRepo;
		this.productRepo = productRepo;
		this.cartRepo = cartRepo;		
	}
	
	@GetMapping("/cart")
	public String cart(Model model)
	{		
		model.addAttribute("categoryList", categoryRepo.findAll());
		model.addAttribute("productList", cartRepo.getCart(1));	
		model.addAttribute("totalByProduct", productRepo.findTotalPriceOfProduct(1));
		model.addAttribute("cartCount", productRepo.countByStatu(1).size());
		model.addAttribute("totalAmount", productRepo.findByPstatuContains(1));		
		
		return "cart";
	}
	
	int stPid=0;
	String page="";
	@GetMapping("/cartremove/{pid}")
	public String cart(@PathVariable String pid, Model model)
	{		
		try {
			stPid  = Integer.parseInt(pid);
			Optional<Product> oproduct = productRepo.findById(stPid);
			oproduct.ifPresent(item -> { 
								
				if(item.getPstatu() == 1)
				{		
					item.setPstatu(0);
					item.setQuantity(0);					
					productRepo.saveAndFlush(item);
				}
				model.addAttribute("detail", item);
				model.addAttribute("productList", productRepo.findAll());
				model.addAttribute("categoryList", categoryRepo.findAll());
				page = "redirect:/cart";
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
	
	@GetMapping("/quantityDecrease/{pid}")
	public String quantityDecrease(@PathVariable String pid, Model model)
	{		
		try {
			stPid  = Integer.parseInt(pid);
			Optional<Product> oproduct = productRepo.findById(stPid);
			oproduct.ifPresent(item -> {
								
				if(item.getQuantity() >= 1)
				{		
					item.setQuantity(item.getQuantity()-1);										
					productRepo.saveAndFlush(item);
					if(item.getQuantity()==0)
					{
						item.setPstatu(0);
						productRepo.saveAndFlush(item);
					}
				}
				model.addAttribute("detail", item);
				model.addAttribute("productList", productRepo.findAll());
				model.addAttribute("categoryList", categoryRepo.findAll());
				page = "redirect:/cart";
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
	
	@GetMapping("/quantityIncrease/{pid}")
	public String quantityIncrease(@PathVariable String pid, Model model)
	{		
		try {
			stPid  = Integer.parseInt(pid);
			Optional<Product> oproduct = productRepo.findById(stPid);
			oproduct.ifPresent(item -> { 
										
				item.setQuantity(item.getQuantity()+1);										
				productRepo.saveAndFlush(item);					
				
				model.addAttribute("detail", item);
				model.addAttribute("productList", productRepo.findAll());
				model.addAttribute("categoryList", categoryRepo.findAll());
				page = "redirect:/cart";
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
	
	@GetMapping("/checkout")
	public String checkout( Model model)
	{		
		try {
			
			 List<Product> plist = productRepo.countByStatu(1);
			 for (Product product : plist) {
				product.setPstatu(0);
				productRepo.saveAndFlush(product);
			}
			 
			page = "redirect:/cart";		
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}	
	
}