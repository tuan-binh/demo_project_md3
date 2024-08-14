package com.ra.demo_project_md3.controller.admin;

import com.ra.demo_project_md3.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController
{
	private final IUserService userService;
	private final HttpSession session;
	
	@GetMapping
	public String viewUser(
			  @RequestParam(name = "page", defaultValue = "0") Integer page,
			  @RequestParam(name = "size", defaultValue = "5") Integer size,
			  @RequestParam(name = "search", defaultValue = "") String search,
			  Model model
	)
	{
		// set active sidebar
		session.setAttribute("active_sidebar", "user");
		// set list user pagination
		model.addAttribute("users", userService.findAllUsers(page, size, search));
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("search", search);
		// totalPages
		Double totalPages = Math.ceil((double) userService.totalAllUsers(search) / size);
		model.addAttribute("totalPages", totalPages);
		return "admin/userManager";
	}
	
	@GetMapping("/{userId}/status")
	public String handleChangeStatus(@PathVariable Long userId)
	{
		userService.changeStatus(userId);
		return "redirect:/admin/user";
	}
	
}
