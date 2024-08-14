package com.ra.demo_project_md3.controller.admin;

import com.ra.demo_project_md3.service.ITypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/service")
@RequiredArgsConstructor
public class TypeController
{
	private final HttpSession session;
	private final ITypeService typeService;
	
	@GetMapping
	public String viewService(
			  @RequestParam(defaultValue = "0") Integer page,
			  @RequestParam(defaultValue = "3") Integer size,
			  @RequestParam(defaultValue = "") String search,
			  Model model
	)
	{
		session.setAttribute("active_sidebar", "service");
		// set pagination
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("search", search);
		model.addAttribute("types", typeService.findAllType(page, size, search));
		// set totalPages time
		Double totalPages = Math.ceil((double) typeService.totalType(search) / size);
		model.addAttribute("totalPages", totalPages);
		return "admin/serviceManager";
	}
	
	
}
