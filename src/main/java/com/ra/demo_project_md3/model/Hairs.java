package com.ra.demo_project_md3.model;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Hairs
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "my_url")
	private String url;
	@Column(name = "my_row")
	private Integer row;
	private Boolean status;
}
