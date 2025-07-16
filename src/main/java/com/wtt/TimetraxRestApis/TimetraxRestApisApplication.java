package com.wtt.TimetraxRestApis;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Timetrax REST APIs", 
		version = "1.0", 
		description = "Timetrax REST APIs Documentation",
		contact = @Contact(name = ": WTT",
		email = "rrajawat@web-tech-talk.com")),
        servers = @Server(url = "http://localhost:8080",
        description = "Local Server")
        //,
//        externalDocs = @ExternalDocumentation(
//        		        description = "Timetrax-Timesheet Application",
//        		        url = "")
)
    
public class TimetraxRestApisApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(TimetraxRestApisApplication.class, args);
	}

}
