package com.ra.demo_project_md3.controller.admin;

import com.ra.demo_project_md3.dto.request.TimeRequest;
import com.ra.demo_project_md3.service.ITimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/time")
@RequiredArgsConstructor
public class TimeController
{
	private final HttpSession session;
	private final ITimeService timeService;
	
	@GetMapping
	public String viewTime(
			  @RequestParam(name = "page", defaultValue = "0") Integer page,
			  @RequestParam(name = "size", defaultValue = "5") Integer size,
			  @RequestParam(name = "search", defaultValue = "") String search,
			  Model model
	)
	{
		// set session active_sidebar
		session.setAttribute("active_sidebar", "time");
		// set pagination
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("times", timeService.findAllTime(page, size, search));
		model.addAttribute("search", search);
		// set totalPages time
		Double totalPages = Math.ceil((double) timeService.totalPagesTime(search) / size);
		model.addAttribute("totalPages", totalPages);
		return "admin/timeManager";
	}
	
	@PostMapping
	public String handleAddTime(@ModelAttribute TimeRequest timeRequest)
	{
		timeService.save(timeRequest);
		return "redirect:/admin/time";
	}
	
	@GetMapping("/{timeId}/status")
	public String changeStatus(@PathVariable Long timeId)
	{
		timeService.changeStatus(timeId);
		return "redirect:/admin/time";
	}
	
}
