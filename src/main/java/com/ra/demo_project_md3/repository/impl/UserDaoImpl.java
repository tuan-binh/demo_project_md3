package com.ra.demo_project_md3.repository.impl;

import com.ra.demo_project_md3.constants.RoleName;
import com.ra.demo_project_md3.model.Users;
import com.ra.demo_project_md3.repository.IUserDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements IUserDao
{
	private final SessionFactory sessionFactory;
	
	@Override
	public List<Users> findAll(int page, int size, String search)
	{
		try (Session session = sessionFactory.openSession())
		{
			if (search.isEmpty())
			{
				return session
						  .createNativeQuery("select * from users u join user_role ur on u.id = ur.user_id join roles r on ur.role_id = r.id where r.roleName not like :roleName limit :limit offset :offset", Users.class)
						  .setParameter("roleName", RoleName.ROLE_ADMIN.toString())
						  .setParameter("limit", size)
						  .setParameter("offset", page * size)
						  .getResultList();
			}
			else
			{
				return session
						  .createNativeQuery("select * from users u join user_role ur on u.id = ur.user_id join roles r on ur.role_id = r.id where u.fullName like :search and r.roleName not like :roleName limit :limit offset :offset", Users.class)
						  .setParameter("search", search)
						  .setParameter("roleName", RoleName.ROLE_ADMIN.toString())
						  .setParameter("limit", size)
						  .setParameter("offset", page * size)
						  .getResultList();
			}
			
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Integer totalAllUsers(String search)
	{
		try (Session session = sessionFactory.openSession())
		{
			if (search.isEmpty())
			{
				return session.createNativeQuery("select * from users u join user_role ur on u.id = ur.user_id join roles r on ur.role_id = r.id where r.roleName not like :roleName", Users.class)
						  .setParameter("roleName", RoleName.ROLE_ADMIN.toString())
						  .getResultList().size();
			}
			else
			{
				return session.createNativeQuery("select * from users u join user_role ur on u.id = ur.user_id join roles r on ur.role_id = r.id where u.fullName like :search and r.roleName not like :roleName", Users.class)
						  .setParameter("search", search)
						  .setParameter("roleName", RoleName.ROLE_ADMIN.toString())
						  .getResultList().size();
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Users findById(Long idFind)
	{
		try (Session session = sessionFactory.openSession())
		{
			return session.find(Users.class, idFind);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean changeStatus(Long userId)
	{
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession())
		{
			transaction = session.beginTransaction();
			Users users = findById(userId);
			users.setStatus(!users.getStatus());
			session.update(users);
			transaction.commit();
			return true;
		}
		catch (Exception e)
		{
			transaction.rollback();
			return false;
		}
	}
}
