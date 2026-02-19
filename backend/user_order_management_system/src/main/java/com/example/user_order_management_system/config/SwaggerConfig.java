//package org.example.finalprojectweb.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import java.util.Collections;
//
//@Configuration
//public class SwaggerConfig {
//
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//    private Info apiInfo(){
//        return new Info().title("hotel - management").description("Hotel Application ").version("1")
//                .contact(new Contact().name("Yousef Injass, Abdulrahman Omar")
//                        .url( "www.abood-Tecnologies.ps")
//                        .email( "injasyousef5@gmail.com"));
//    }
//
//    @Bean
//    public OpenAPI api(){
//        return new OpenAPI()
//                .components(
//                        new Components().addSecuritySchemes(AUTHORIZATION_HEADER,
//                                new SecurityScheme().type(SecurityScheme.Type.APIKEY)
//                                        .in(SecurityScheme.In.HEADER)
//                                        .name(AUTHORIZATION_HEADER)
//                                        .bearerFormat("JWT")))
//
//                .info(apiInfo())
//
//                .addSecurityItem(
//                        new SecurityRequirement().addList(AUTHORIZATION_HEADER, Collections.singletonList("global")));
//
//    }
//
//}
package com.example.user_order_management_system.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Web Services Final Project API")
                        .version("1.0")
                        .description("API documentation for Web Services Final Project"));
    }
}