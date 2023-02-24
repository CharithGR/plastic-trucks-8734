package com.masai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@EnableSwagger2
@OpenAPIDefinition(info = @Info(title = "MyApi info", version = "2.0" , description = "myDescription") )
public class OnlineTripManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTripManagementSystemApplication.class, args);
	}

}
