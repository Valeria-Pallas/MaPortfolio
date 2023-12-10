package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {

    final String SecuritySchemeName = "bearerAuth";
    final String apiTitle = "Portfolio API";
    final String apiDescription = "API for Portfolio";
    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(SecuritySchemeName))
        .components(
              new Components().addSecuritySchemes(SecuritySchemeName,
                    new SecurityScheme().name(SecuritySchemeName).type(SecurityScheme.Type.HTTP)
                            .scheme("bearer").bearerFormat("JWT")))
        .info(new Info().title(apiTitle).version("1.0").description(apiDescription));
  }
}
