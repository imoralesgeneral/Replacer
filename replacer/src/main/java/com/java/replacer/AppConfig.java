package com.java.replacer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.replacer.executor.Executor;
import com.java.replacer.io.Writer;
import com.java.replacer.rules.Rules;

@Configuration
public class AppConfig {

	@Bean("executor")
	public Executor executor() {
		return new Executor();
	}
	
	@Bean("writer")
	public Writer writer() {
		return new Writer();
	}
	
	@Bean("rules")
	public Rules rules() {
		return new Rules();
	}
	
}
