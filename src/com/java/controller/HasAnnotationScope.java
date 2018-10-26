package com.java.controller;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class HasAnnotationScope implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//return metadata.isAnnotated(Scope.class.getName());
		Map<String, Object> attributes= metadata.getAnnotationAttributes(Scope.class.getName());
		if(attributes!= null) {
		String value=(String) attributes.get("value");
		return "singleton".equalsIgnoreCase(value);
		}
		return false;
	}

}
