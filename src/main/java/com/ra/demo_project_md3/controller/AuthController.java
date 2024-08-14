package com.ra.demo_project_md3.controller;

import com.ra.demo_project_md3.constants.RoleName;
import com.ra.demo_project_md3.dto.request.FormLogin;
import com.ra.demo_project_md3.dto.request.FormRegister;
import com.ra.demo_project_md3.model.Users;
import com.ra.demo_project_md3.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
	private final IAuthService authService;
	
	@GetMapping("/login")
	public String viewLogin(Model model)
	{
		model.addAttribute("formLogin", new FormLogin());
		return "login";
	}
	
	@PostMapping("/login")
	public String handleLogin(@Valid @ModelAttribute("formLogin") FormLogin formLogin, BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors())
		{
			model.addAttribute("formLogin", formLogin);
			return "login";
		}
		Users users = authService.handleLogin(formLogin);
		if (users != null)
		{
			if (users.getRole().stream().anyMatch(roles -> roles.getRoleName().equals(RoleName.ROLE_ADMIN)))
			{
				return "redirect:/admin";
			}
			return "redirect:/";
		}
		model.addAttribute("error", "phone or password is incorrect");
		model.addAttribute("formLogin", formLogin);
		return "login";
	}
	
	@GetMapping("/register")
	public String viewRegister(Model model)
	{
		model.addAttribute("formRegister", new FormRegister());
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegister(@Valid @ModelAttribute("formRegister") FormRegister formRegister, BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors())
		{
			model.addAttribute("formRegister", formRegister);
			return "register";
		}
		authService.handleRegister(formRegister);
		return "redirect:/auth/login";
	}
	
	@GetMapping("/logout")
	public String handleLogout()
	{
		authService.handleLogout();
		return "redirect:/";
	}
	
}
