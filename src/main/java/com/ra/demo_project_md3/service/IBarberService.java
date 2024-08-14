package com.ra.demo_project_md3.service;

import com.ra.demo_project_md3.dto.request.BarberRequest;
import com.ra.demo_project_md3.model.Barbers;

import java.util.List;

public interface IBarberService
{
	List<Barbers> findAllBarbers(int page, int size, String search);
	
	Long totalAllBarbers(String search);
	
	void addBarber(BarberRequest barberRequest);
	
	boolean changeStatus(Long barberId);
	
	List<Barbers> findAll();
}
