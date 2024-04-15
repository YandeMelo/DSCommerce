package com.yandemelo.dscommercePGAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "DSCommerce", version = "1.0", description = "API desenvolvida para o aprendizado de Spring e Spring Boot"))
public class DscommercePgAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(DscommercePgAdminApplication.class, args);
	}

}
