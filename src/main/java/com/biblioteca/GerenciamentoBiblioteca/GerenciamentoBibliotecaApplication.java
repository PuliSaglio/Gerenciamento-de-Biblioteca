package com.biblioteca.GerenciamentoBiblioteca;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gerenciamento de Biblioteca", version = "1.0", description = "API para gerenciamento de biblioteca"))
public class GerenciamentoBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoBibliotecaApplication.class, args);
	}

}
