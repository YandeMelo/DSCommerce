package com.yandemelo.dscommercePGAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "DSCommerce", version = "1.0", description = "API desenvolvida para um sistema de comércio online"))
@SpringBootApplication
public class DscommercePgAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(DscommercePgAdminApplication.class, args);
	}

}
