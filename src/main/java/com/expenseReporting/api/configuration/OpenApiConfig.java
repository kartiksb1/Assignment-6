package com.expenseReporting.api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI expenseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Expense Reporting API")
                        .description("REST API for managing employee expenses")
                        .version("v1")
                        .contact(new Contact()
                                .name("Backend Team")
                                .email("backend@company.com")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080")
                ));
    }
}
