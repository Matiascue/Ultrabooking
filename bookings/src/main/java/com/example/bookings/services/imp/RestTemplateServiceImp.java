package com.example.bookings.services.imp;

import com.example.bookings.dtos.client.FlightDto;
import com.example.bookings.services.RestTemplateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class RestTemplateServiceImp implements RestTemplateService {
    @Autowired
    private WebClient webClient;

    @Override
    public ResponseEntity<FlightDto> getFlightById(String id) {
        try {
            FlightDto flight = webClient
                    .get()
                    .uri("http://api-gateway:8080/flights/flights/{id}", id)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(), response ->
                            Mono.error(new EntityNotFoundException("Flight not found with id: " + id)))
                    .onStatus(status -> status.is5xxServerError(), response ->
                            Mono.error(new RuntimeException("Server error occurred")))
                    .bodyToMono(FlightDto.class)
                    .block();

            return ResponseEntity.ok(flight);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @Override
    public ResponseEntity<FlightDto> postFlight(FlightDto flightDto) {
        try {
            FlightDto createdFlight = webClient
                    .post()
                    .uri("http://api-gateway:8080/flights/flights")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(flightDto)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(), response ->
                            Mono.error(new IllegalArgumentException("Error creating flight")))
                    .onStatus(status -> status.is5xxServerError(), response ->
                            Mono.error(new RuntimeException("Server error occurred")))
                    .bodyToMono(FlightDto.class)
                    .block();

            return ResponseEntity.ok(createdFlight);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
