package com.ra.demo_project_md3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TimeRequest
{
	@NotEmpty(message = "time must be not null")
	private String time;
}
