package com.ra.demo_project_md3.repository.impl;

import com.ra.demo_project_md3.dto.request.FormLogin;
import com.ra.demo_project_md3.model.Users;
import com.ra.demo_project_md3.repository.IAuthDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthDaoImpl implements IAuthDao
{
	private final SessionFactory sessionFactory;
	
	@Override
	public Users handleLogin(FormLogin formLogin)
	{
		try (Session session = sessionFactory.openSession())
		{
			Users users = session.createQuery("select u from Users u where u.phone like :phone", Users.class)
					  .setParameter("phone", formLogin.getPhone())
					  .getSingleResult();
			if (users == null)
			{
				return null;
			}
			if (BCrypt.checkpw(formLogin.getPassword(), users.getPassword()))
			{
				return users;
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return null;
	}
	
	@Override
	public void handleRegister(Users users)
	{
		Session session = sessionFactory.openSession();
		try
		{
			session.beginTransaction();
			session.save(users);
			session.getTransaction().commit();
		}
		catch (Exception e)
		{
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
}
