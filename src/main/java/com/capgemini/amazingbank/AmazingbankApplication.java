package com.capgemini.amazingbank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@OpenAPIDefinition(info = @Info(title = "CAPGEMINI - AMAZING BANK",
        description = "Capgemini Amazing Bank API Documentation.",
        contact = @Contact(email = "junidryves@yahoo.fr"), version = "1.0"))
@SpringBootApplication
public class AmazingbankApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmazingbankApplication.class, args);
    }
}
