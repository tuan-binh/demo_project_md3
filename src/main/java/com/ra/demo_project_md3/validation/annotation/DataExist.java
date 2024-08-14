package com.ra.demo_project_md3.validation.annotation;

import com.ra.demo_project_md3.validation.handle.HandleDataExist;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = HandleDataExist.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataExist
{
	String message() default "name is already exist";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	Class<?> entityClass();
	
	String existName();
}
