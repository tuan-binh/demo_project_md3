package com.ra.demo_project_md3.controller.admin;

import com.ra.demo_project_md3.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController
{
	private final IUserService userService;
	private final HttpSession session;
	
	@GetMapping
	public String viewAdmin()
	{
		session.setAttribute("active_sidebar", "home");
		return "admin/index";
	}
	
	
	@GetMapping("/order")
	public String viewOrder()
	{
		session.setAttribute("active_sidebar", "order");
		return "admin/orderManager";
	}
	
	@GetMapping("/hair")
	public String viewHair()
	{
		session.setAttribute("active_sidebar", "hair");
		return "admin/hairManager";
	}
	
}
