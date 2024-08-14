package com.ra.demo_project_md3.validation.handle;

import com.ra.demo_project_md3.validation.annotation.DataExist;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class HandleDataExist implements ConstraintValidator<DataExist, String>
{
	private final SessionFactory sessionFactory;
	private String existName;
	private Class<?> entityClass;
	
	@Override
	public void initialize(DataExist constraintAnnotation)
	{
		this.entityClass = constraintAnnotation.entityClass();
		this.existName = constraintAnnotation.existName();
	}
	
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
	{
		Session session = sessionFactory.openSession();
		try
		{
			return session.createQuery("select count (e) from " + entityClass.getSimpleName() + " e where  e." + existName + " = :value", Long.class)
					  .setParameter("value", s).getSingleResult() == 0;
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
