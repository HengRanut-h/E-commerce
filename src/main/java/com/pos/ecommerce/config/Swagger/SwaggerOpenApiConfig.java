package com.pos.ecommerce.config.Swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerOpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
//                .addSecurityItem(securityRequirement())
//                .components(components())
                .externalDocs(externalDocs())
                .servers(servers());
    }
    @Bean
    public GroupedOpenApi frontendGroup() {
        return GroupedOpenApi
                .builder()
                .group("frontend-api")
                .addOpenApiCustomizer(openApi -> openApi
                        .info(getFrontendApiInfo())
                )
                .packagesToScan("com.pos.ecommerce")// scan controller
                .build();
    }
    private Info getFrontendApiInfo() {
        Contact contact = new Contact();
        contact.setName("Heng Ranut");
        contact.setEmail("ranutheng5@gmail.com");
        contact.setUrl("Heng Ranut");

        return new Info().title("Upload API").description("view Upload API").contact(contact).version("1.0.0");
    }

    // ── INFO ───────────────────────────────

    private Info apiInfo() {
        return new Info()
                .title("My App API")
                .description("""
                        REST API Documentation
                        
                        Standard response format:
                        {
                          "success": true,
                          "status_code": 200,
                          "code": "2000",
                          "message": "Success",
                          "data": {}
                        }
                        """)
                .version("v1.0.0")
                .contact(new Contact()
                        .name("Backend Team")
                        .email("dev@myapp.com")
                        .url("https://myapp.com"))
                .license(new License()
                        .name("MIT License")
                        .url("https://opensource.org/licenses/MIT"))
                .termsOfService("https://myapp.com/terms");

    }

    // ── SECURITY ───────────────────────────

//    private SecurityRequirement securityRequirement() {
//        return new SecurityRequirement().addList("bearerAuth");
//    }
//
//    private Components components() {
//        return new Components()
//
//                // JWT
//                .addSecuritySchemes("bearerAuth",
//                        new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")
//                                .description("JWT token: Bearer <token>"))
//
//                // API Key
//                .addSecuritySchemes("apiKey",
//                        new SecurityScheme()
//                                .type(SecurityScheme.Type.APIKEY)
//                                .in(SecurityScheme.In.HEADER)
//                                .name("X-API-Key"))
//
//                // Reusable responses
//                .addResponses("Unauthorized",
//                        new ApiResponse().description("401 Unauthorized"))
//                .addResponses("Forbidden",
//                        new ApiResponse().description("403 Forbidden"))
//                .addResponses("NotFound",
//                        new ApiResponse().description("404 Not Found"))
//                .addResponses("ValidationError",
//                        new ApiResponse().description("422 Validation Error"))
//                .addResponses("InternalError",
//                        new ApiResponse().description("500 Internal Server Error"));
//    }

    // ── SERVERS ────────────────────────────

    private List<Server> servers() {
        return List.of(
                new Server().url("http://localhost:8084").description("Local"),
                new Server().url("https://staging-api.myapp.com").description("Staging"),
                new Server().url("https://api.myapp.com").description("Production")
        );
    }

    // ── DOCS ───────────────────────────────

    private ExternalDocumentation externalDocs() {
        return new ExternalDocumentation()
                .description("Full developer documentation")
                .url("https://docs.myapp.com");
    }
}