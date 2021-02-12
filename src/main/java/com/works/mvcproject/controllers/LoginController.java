package com.works.mvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.works.mvcproject.models.Admin;
import com.works.mvcproject.utils.AdminControl;

@Controller
public class LoginController {

	String error = "";	
	final AdminControl adminControl;

	public LoginController(AdminControl adminControl) {
		
		this.adminControl = adminControl;		
	}		

	@GetMapping({"/","login"})
	public String login(Model model)
	{				
		if(!error.equals(""))
		{
			model.addAttribute("error", error);
		}
		error = "";		
		
		return "login";
	}
	
	@PostMapping("/adminLogin")
	public String adminLogin(Admin admin,Model model)
	{	
		if(adminControl.control(admin) != null)
		{						
			return "redirect:/dashboard";
		}
		error = "Mail or Password is Wrong";
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		adminControl.adminLogout();
		
		return "redirect:/";
	}

}