package com.ra.demo_project_md3.repository.impl;

import com.ra.demo_project_md3.model.Barbers;
import com.ra.demo_project_md3.model.Users;
import com.ra.demo_project_md3.repository.IBarberDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BarberDaoImpl implements IBarberDao
{
	private final SessionFactory sessionFactory;
	
	@Override
	public List<Barbers> findAllBarbers(int page, int size, String search)
	{
		Session session = sessionFactory.openSession();
		try
		{
			String hql = "from Barbers";
			if (!search.isEmpty())
			{
				hql += " b where b.barberName like concat('%',:search,'%')";
			}
			List<Barbers> barbers;
			if (search.isEmpty())
			{
				barbers = session.createQuery(hql, Barbers.class)
						  .setFirstResult(page * size)
						  .setMaxResults(size)
						  .getResultList();
			}
			else
			{
				barbers = session.createQuery(hql, Barbers.class)
						  .setParameter("search", search)
						  .setFirstResult(page * size)
						  .setMaxResults(size)
						  .getResultList();
			}
			return barbers;
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
	
	@Override
	public Long totalAllBarbers(String search)
	{
		Session session = sessionFactory.openSession();
		try
		{
			if (search.isEmpty())
			{
				return session.createQuery("select count(b) from Barbers b", Long.class)
						  .getSingleResult();
			}
			else
			{
				return session.createQuery("select count(b) from Barbers b where b.barberName like concat('%',:search,'%') ", Long.class)
						  .setParameter("search", search)
						  .getSingleResult();
			}
			
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
	
	@Override
	public void save(Barbers barbers)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			if (barbers.getId() == null)
			{
				// chức năng thêm mới
				session.save(barbers);
			}
			else
			{
				// chức năng sửa
				session.update(barbers);
			}
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw new RuntimeException(e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public Barbers findById(Long barberId)
	{
		try (Session session = sessionFactory.openSession())
		{
			return session.find(Barbers.class, barberId);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean changeStatus(Long barberId)
	{
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession())
		{
			transaction = session.beginTransaction();
			Barbers barbers = findById(barberId);
			barbers.setStatus(!barbers.getStatus());
			session.update(barbers);
			transaction.commit();
			return true;
		}
		catch (Exception e)
		{
			transaction.rollback();
			return false;
		}
	}
	
	@Override
	public List<Barbers> findAll()
	{
		try (Session session = sessionFactory.openSession())
		{
			return session.createQuery("from Barbers ", Barbers.class).getResultList();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
