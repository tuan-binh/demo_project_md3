package com.ra.demo_project_md3.repository.impl;

import com.ra.demo_project_md3.model.Type;
import com.ra.demo_project_md3.repository.ITypeDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TypeDaoImpl implements ITypeDao
{
	private final SessionFactory sessionFactory;
	
	@Override
	public List<Type> findAllType(int page, int size, String search)
	{
		try (Session session = sessionFactory.openSession())
		{
			String hql = "from Type";
			if (!search.isEmpty())
			{
				hql += " t where t.typeName like concat('%',:search,'%')";
			}
			List<Type> typeList;
			if (search.isEmpty())
			{
				typeList = session.createQuery(hql, Type.class)
						  .setFirstResult(page * size)
						  .setMaxResults(size)
						  .getResultList();
			}
			else
			{
				typeList = session.createQuery(hql, Type.class)
						  .setParameter("search", search)
						  .setFirstResult(page * size)
						  .setMaxResults(size)
						  .getResultList();
			}
			return typeList;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Long totalType(String search)
	{
		try (Session session = sessionFactory.openSession())
		{
			if (search.isEmpty())
			{
				return session.createQuery("select count(t) from Type t", Long.class)
						  .getSingleResult();
			}
			else
			{
				return session.createQuery("select count(t) from Type t where t.typeName like concat('%',:search,'%') ", Long.class)
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
	public List<Type> findAll()
	{
		try (Session session = sessionFactory.openSession())
		{
			return session.createQuery("from Type", Type.class).getResultList();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
