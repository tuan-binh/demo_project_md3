package com.ra.demo_project_md3.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ra")
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware
{
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}
	
	//Cấu hình Thymleaf
	@Bean
	public SpringResourceTemplateResolver templateResolver()
	{
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine()
	{
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver viewResolver()
	{
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setContentType("UTF-8");
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/css/**", "/js/**", "/img/**")
				  .addResourceLocations("classpath:/assets/css/", "classpath:/assets/js/", "classpath:/assets/img/");
		
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSizePerFile(24 * 1024 * 1024);
		multipartResolver.setMaxUploadSize(5 * 1024 * 1024);
		return multipartResolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new AdminInterceptors()).addPathPatterns("/admin/**");
		registry.addInterceptor(new UserInterceptors()).addPathPatterns("/user/**");
	}
	
	@Bean
	public Storage storage() throws IOException
	{
		InputStream inputStream = new ClassPathResource("firebase-config.json").getInputStream();
		return StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(inputStream))
				  .build()
				  .getService();
	}
}
