package com.ra.demo_project_md3.dto.request;

import com.ra.demo_project_md3.model.Users;
import com.ra.demo_project_md3.validation.annotation.DataExist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormRegister
{
	@NotEmpty(message = "fullName must be not empty")
	private String fullName;
	@NotEmpty(message = "phone must be not empty")
	@DataExist(message = "phone is already exist", entityClass = Users.class, existName = "phone")
	private String phone;
	@NotEmpty(message = "password must me not empty")
	private String password;
}
