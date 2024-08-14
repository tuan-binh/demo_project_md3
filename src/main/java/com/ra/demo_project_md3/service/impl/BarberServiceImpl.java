package com.ra.demo_project_md3.service.impl;

import com.ra.demo_project_md3.dto.request.BarberRequest;
import com.ra.demo_project_md3.model.Barbers;
import com.ra.demo_project_md3.repository.IBarberDao;
import com.ra.demo_project_md3.service.common.FileService;
import com.ra.demo_project_md3.service.IBarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarberServiceImpl implements IBarberService
{
	private final IBarberDao barberDao;
	private final FileService fileService;
	
	@Override
	public List<Barbers> findAllBarbers(int page, int size, String search)
	{
		return barberDao.findAllBarbers(page, size, search);
	}
	
	@Override
	public Long totalAllBarbers(String search)
	{
		return barberDao.totalAllBarbers(search);
	}
	
	@Override
	public void addBarber(BarberRequest barberRequest)
	{
		String avatar = fileService.uploadFileToServer(barberRequest.getAvatar());
		Barbers barbers = new Barbers();
		barbers.setBarberName(barberRequest.getName());
		barbers.setUrlAvatar(avatar);
		barbers.setStatus(true);
		barberDao.save(barbers);
	}
	
	@Override
	public boolean changeStatus(Long barberId)
	{
		return barberDao.changeStatus(barberId);
	}
	
	@Override
	public List<Barbers> findAll()
	{
		return barberDao.findAll();
	}
}
