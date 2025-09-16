package com.gateway.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route("clientes_service", r -> r.path("/user/**")
                        .uri("http://user-service:8081"))
                .route("flight_service", r -> r.path("/flights/**")
                        .uri("http://flight-service:8082"))
                .route("airport_service", r -> r.path("/airport/**")
                        .uri("http://flight-service:8082"))
                .route("booking_service", r -> r.path("/booking/**")
                        .uri("http://booking-service:8083")).build();
    }
    /*
    * Filtro CORS global para el Gateway
    */
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");  // Permitir todos los orígenes
        config.addAllowedMethod("*");  // Permitir todos los métodos (GET, POST, OPTIONS, etc.)
        config.addAllowedHeader("*");  // Permitir todos los encabezados
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }


}
