package com.ra.demo_project_md3.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Orders
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "barber_id")
	private Barbers barber;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private Type type;
	
	@ManyToOne
	@JoinColumn(name = "time_id")
	private Times time;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	private String customerName;
	
	private String customerPhone;
	
	private String date;
	
	private Boolean status;
}
