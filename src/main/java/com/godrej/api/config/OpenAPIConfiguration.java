package com.godrej.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Value("${swagger_url}")
    private String swagger_url;
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl(swagger_url);
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Godrej");
        myContact.setEmail("your.email@gmail.com");

        Info information = new Info()
                .title("Customer Managment System")
                .version("1.0")
                .description("This API exposes endpoints to manage employees.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
