package com.ra.demo_project_md3.repository;

import com.ra.demo_project_md3.model.Barbers;

import java.util.List;

public interface IBarberDao
{
	List<Barbers> findAllBarbers(int page, int size, String search);
	
	Long totalAllBarbers(String search);
	
	void save(Barbers barbers);
	
	Barbers findById(Long barberId);
	
	boolean changeStatus(Long barberId);
	
	List<Barbers> findAll();
}
