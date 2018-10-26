package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.java.controller.NotTestCondition;
import com.java.controller.TestCondition;
@Configuration
@EnableWebMvc
@ComponentScan("com.java")
@EnableAspectJAutoProxy(proxyTargetClass=true)//cglib proxy: true | false: jdk 
public class SpringConfig {

	@Bean
	@Scope("singleton")
	public BeanNameUrlHandlerMapping getHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
	}
	
	
	@Conditional(TestCondition.class)
	@Bean("vr")
	public ViewResolver getResolverDev() {
		System.out.println("In test");
		return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
		
	}
	
	@Conditional(NotTestCondition.class)
	@Bean("vr")
	public ViewResolver getResolverDefault() {
		System.out.println("Not in test");
		return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
	}
	
}
