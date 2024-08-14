package com.ra.demo_project_md3.repository.impl;

import com.ra.demo_project_md3.model.Times;
import com.ra.demo_project_md3.repository.ITimeDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TimeDaoImpl implements ITimeDao
{
	private final SessionFactory sessionFactory;
	
	@Override
	public List<Times> findAllTime(int page, int size, String search)
	{
		try (Session session = sessionFactory.openSession())
		{
			String hql = "from Times";
			if (!search.isEmpty())
			{
				hql += " t where t.time like concat('%',:search,'%')";
			}
			List<Times> times;
			if (search.isEmpty())
			{
				times = session.createQuery(hql, Times.class)
						  .setFirstResult(page * size)
						  .setMaxResults(size)
						  .getResultList();
			}
			else
			{
				times = session.createQuery(hql, Times.class)
						  .setParameter("search", search)
						  .setFirstResult(page * size)
						  .setMaxResults(size)
						  .getResultList();
			}
			return times;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Long totalAllTime(String search)
	{
		try (Session session = sessionFactory.openSession())
		{
			if (search.isEmpty())
			{
				return session.createQuery("select count(t) from Times t", Long.class)
						  .getSingleResult();
			}
			else
			{
				return session.createQuery("select count(t) from Times t where t.time like concat('%',:search,'%') ", Long.class)
						  .setParameter("search", search)
						  .getSingleResult();
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void save(Times times)
	{
		
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession())
		{
			transaction = session.beginTransaction();
			if (times.getId() == null)
			{
				times.setStatus(true);
				session.save(times);
			}
			else
			{
				session.update(times);
			}
			transaction.commit();
		}
		catch (Exception e)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Times findById(Long id)
	{
		try (Session session = sessionFactory.openSession())
		{
			return session.find(Times.class, id);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void changeStatus(Long timeId)
	{
		
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession())
		{
			transaction = session.beginTransaction();
			Times times = findById(timeId);
			times.setStatus(!times.getStatus());
			session.update(times);
			transaction.commit();
		}
		catch (Exception e)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Times> findAll()
	{
		try (Session session = sessionFactory.openSession())
		{
			return session.createQuery("from Times", Times.class).getResultList();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
