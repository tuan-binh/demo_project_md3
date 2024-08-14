package com.ra.demo_project_md3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormLogin
{
	@NotEmpty(message = "phone must be not empty")
	private String phone;
	@NotEmpty(message = "password must me not empty")
	private String password;
	
}
