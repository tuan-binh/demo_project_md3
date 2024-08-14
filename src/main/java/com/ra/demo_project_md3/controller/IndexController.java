package com.ra.demo_project_md3.controller;

import com.ra.demo_project_md3.service.IAddressService;
import com.ra.demo_project_md3.service.IBarberService;
import com.ra.demo_project_md3.service.ITimeService;
import com.ra.demo_project_md3.service.ITypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class IndexController
{
	private final IAddressService addressService;
	private final IBarberService barberService;
	private final ITypeService typeService;
	private final ITimeService timeService;
	
	@GetMapping("/")
	public String index()
	{
		return "users/index";
	}
	
	@GetMapping("/service")
	public String service()
	{
		return "users/service";
	}
	
	@GetMapping("/location")
	public String location()
	{
		return "users/location";
	}
	
	@GetMapping("/403")
	public String _403()
	{
		return "accessDeniedPage";
	}
	
	@GetMapping("/404")
	public String _404()
	{
		return "notFoundPage";
	}
	
	@PostMapping("/order")
	public String order(@RequestParam String phone, Model model)
	{
		System.out.println("phone: " + phone);
		model.addAttribute("phone", phone);
		model.addAttribute("address", addressService.findAll());
		model.addAttribute("types", typeService.findAll());
		model.addAttribute("times", timeService.findAll());
		model.addAttribute("barbers", barberService.findAll());
		return "users/order";
	}
	
}
