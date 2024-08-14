package com.ra.demo_project_md3.controller.admin;

import com.ra.demo_project_md3.dto.request.BarberRequest;
import com.ra.demo_project_md3.service.IBarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/barber")
@RequiredArgsConstructor
public class BarberController
{
	private final HttpSession session;
	private final IBarberService barberService;
	
	@GetMapping
	public String viewBarber(
			  @RequestParam(name = "page", defaultValue = "0") Integer page,
			  @RequestParam(name = "size", defaultValue = "5") Integer size,
			  @RequestParam(name = "search", defaultValue = "") String search,
			  Model model
	)
	{
		// set session active_sidebar
		session.setAttribute("active_sidebar", "barber");
		// set list user pagination
		model.addAttribute("barbers", barberService.findAllBarbers(page, size, search));
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("search", search);
		// totalPages
		Double totalPages = Math.ceil((double) barberService.totalAllBarbers(search) / size);
		model.addAttribute("totalPages", totalPages);
		return "admin/barberManager";
	}
	
	@PostMapping
	public String addBarber(@ModelAttribute BarberRequest barberRequest)
	{
		barberService.addBarber(barberRequest);
		return "redirect:/admin/barber";
	}
	
	@GetMapping("/{barberId}/status")
	public String changeStatus(@PathVariable Long barberId)
	{
		barberService.changeStatus(barberId);
		return "redirect:/admin/barber";
	}
	
}
