package com.works.mvcproject.utils;

import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import com.works.mvcproject.models.Admin;
import com.works.mvcproject.repositories.AdminRepository;

@Configuration
public class AdminControl {	
	
	final AdminRepository aRepo;
	final HttpServletRequest req;
	final HttpServletResponse res;
	
	public AdminControl(AdminRepository aRepo,HttpServletRequest req,HttpServletResponse res)
	{
		this.aRepo = aRepo;		this.req = req;
		this.res = res;
	}
	
	public Admin control(Admin admin)
	{
		String pass = new Util().password(admin.getPass(), 3);
		Admin adm = aRepo.findByMailAndPass(admin.getMail(), pass );
		System.out.println("adm : " +adm);
		if(adm != null)
		{
			req.getSession().setAttribute("admin", adm);
			System.out.println("Remember " + admin.getRemember());
			
			if(admin.getRemember() != null)
			{
				Cookie cookie = new Cookie("admin", ""+ adm.getAid());
				cookie.setMaxAge(60*60*24); // 24 saat
				res.addCookie(cookie);
			}
			
		}		
		return adm;
	}
	
	public void cookieControl()
	{
		if(req.getCookies() != null)
		{
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("admin"))
				{
					String val = cookie.getValue();
					int aid = Integer.parseInt(val);
					Optional<Admin> admin = aRepo.findById(aid);
					if(admin.isPresent())
					{
						req.getSession().setAttribute("admin", admin);
					}
					break;
				}
			}
		}
	}
	
	public void adminLogout()
	{
		req.getSession().removeAttribute("admin");	
		Cookie cookie = new Cookie("admin", "");
		cookie.setMaxAge(0);
		res.addCookie(cookie);
	} 	

}