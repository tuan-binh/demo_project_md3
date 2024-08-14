package com.ra.demo_project_md3.repository.impl;

import com.ra.demo_project_md3.model.Address;
import com.ra.demo_project_md3.repository.IAddressDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressDaoImpl implements IAddressDao
{
	private final SessionFactory sessionFactory;
	
	@Override
	public List<Address> findAllAddress(int page, int size, String search)
	{
		try (Session session = sessionFactory.openSession())
		{
			String hql = "from Address";
			if (!search.isEmpty())
			{
				hql += " a where a.address like concat('%',:search,'%')";
			}
			List<Address> addresses;
			if (search.isEmpty())
			{
				addresses = session.createQuery(hql, Address.class)
						  .setFirstResult(page * size)
						  .setMaxResults(size)
						  .getResultList();
			}
			else
			{
				addresses = session.createQuery(hql, Address.class)
						  .setParameter("search", search)
						  .setFirstResult(page * size)
						  .setMaxResults(size)
						  .getResultList();
			}
			return addresses;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Long totalAllAddress(String search)
	{
		try (Session session = sessionFactory.openSession())
		{
			if (search.isEmpty())
			{
				return session.createQuery("select count(a) from Address a", Long.class)
						  .getSingleResult();
			}
			else
			{
				return session.createQuery("select count(a) from Address a where a.address like concat('%',:search,'%') ", Long.class)
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
	public List<Address> findAll()
	{
		try (Session session = sessionFactory.openSession())
		{
			return session.createQuery("from Address", Address.class).getResultList();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
