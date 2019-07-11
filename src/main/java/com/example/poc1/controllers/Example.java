package com.example.poc1.controllers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController

@Configuration

//For hierarchical properties
@ConfigurationProperties(prefix = "current")

//2. From another file bar.properties in the classpath i.e under resources.
@PropertySource("classpath:bar.properties")

// 3. For Cloud/Microservice architecture Properties
@RefreshScope



public class Example {

	// 1 B. We will set the variables in application.properties from environment.
	@Autowired
	private Environment env;

	// 1 A. Using the @Value annotation, we can inject the values from the
	// application.properties file into class fields in the Spring-managed bean
	// "Example"
	@Value("${user}")
	private String user;

	private String day;
	private String time;

	private String codename;

	@Value("${spring.profiles.active}")
	private String ActiveProfile;

	// 4. Using PropertyPlaceholderConfigurer we get the spring.xml file and its
	// properties.
	// This is a great example of how we can mix properties between Spring boot and
	// Spring.
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spring.xml");
	Database db1 = (Database) ctx.getBean("databasebean");

	// Both the older PropertyPlaceholderConfigurer and the new
	// PropertySourcesPlaceholderConfigurer added in Spring 3.1 resolve ${…}
	// placeholders within bean definition property values and @Value annotations.

	// 5. Using SPRING_APPLICATION_JSON - See assessment for details. But basically
	// we will run this fat jar like so
	// SPRING_APPLICATION_JSON='{"email":"sam@att.com"}' java -jar poc1-0.0.1-SNAPSHOT.jar

	// There are tons of ways to use JSON for configuration.
	// https://www.baeldung.com/spring-boot-json-properties is the best resource.


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	@RequestMapping("/")
	String home() {
		return "Hello " + user + "! " + env.getProperty("user") + ", it is " + time + " on " + day
				+ ". Your code name is " + codename
				+ ". The active spring profile obtained from the Config server which inturn obtained it from the Git Repo is : "
				+ ActiveProfile + ". The db url is " + db1.getDburl() + ". Your email is " + env.getProperty("email");

	}

}