package com.ra.demo_project_md3.controller.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MyInfoController
{
	
	@GetMapping("/information")
	public String information()
	{
		return "users/information";
	}
	
//	@GetMapping("/history")
//	public String history()
//	{
//
//	}
	
}
