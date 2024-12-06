package com.example.xmasshop.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private Info info() {
        return new Info()
                .title("New API Documentation")
                .version("1.0")
                .description("API Documentation");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components( new Components() )
                .info(info());
    }
}
