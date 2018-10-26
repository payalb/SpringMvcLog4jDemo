package com.java.controller;

import java.util.Arrays;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NotTestCondition implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String[] activeProfile=context.getEnvironment().getActiveProfiles();
		
		return !Arrays.stream(activeProfile).anyMatch(x-> "test".equalsIgnoreCase(x));
		
	}

}
