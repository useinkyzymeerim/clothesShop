package com.example.clothesshop.controller.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configure() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clothes Shop")
                        .description("Онлайн магазин одежды")
                        .version("1.0.0")

                );
    }

}
