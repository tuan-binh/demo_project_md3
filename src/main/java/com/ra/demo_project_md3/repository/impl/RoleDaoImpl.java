package com.ra.demo_project_md3.repository.impl;

import com.ra.demo_project_md3.constants.RoleName;
import com.ra.demo_project_md3.model.Roles;
import com.ra.demo_project_md3.repository.IRoleDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleDaoImpl implements IRoleDao
{
	private final SessionFactory sessionFactory;
	
	@Override
	public Roles findByRoleName(RoleName roleName)
	{
		Session session = sessionFactory.openSession();
		try
		{
			return session.createQuery("select r from Roles r where r.roleName = :roleName", Roles.class)
					  .setParameter("roleName", roleName)
					  .getSingleResult();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			session.close();
		}
	}
}
