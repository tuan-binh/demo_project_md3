package com.ra.demo_project_md3.controller.admin;

import com.ra.demo_project_md3.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/address")
@RequiredArgsConstructor
public class AddressController
{
	private final HttpSession session;
	private final IAddressService addressService;
	
	@GetMapping
	public String viewAddress(
			  @RequestParam(name = "page", defaultValue = "0") Integer page,
			  @RequestParam(name = "size", defaultValue = "5") Integer size,
			  @RequestParam(name = "search", defaultValue = "") String search,
			  Model model
	)
	{
		// set active sidebar in session
		session.setAttribute("active_sidebar", "address");
		// set pagination
		model.addAttribute("address", addressService.findAllAdress(page, size,search));
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("search", search);
		// set totalPages
		Double totalPages = Math.ceil((double) addressService.totalAllAddress(search) / size);
		model.addAttribute("totalPages", totalPages);
		
		return "admin/addressManager";
	}
	
}
