package com.ra.demo_project_md3.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Users
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	private String phone;
	private String password;
	private String address;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "user_role",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> role;
	
	@ManyToMany
	@JoinTable(
			  name = "favourite",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "hair_id")
	)
	private Set<Hairs> favourite = new HashSet<>();
	
	private Boolean status;
}
