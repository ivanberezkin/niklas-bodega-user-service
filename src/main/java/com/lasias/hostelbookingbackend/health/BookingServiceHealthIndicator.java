package com.lasias.hostelbookingbackend.health;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Duration;

@Slf4j
@Component
public class BookingServiceHealthIndicator implements HealthIndicator {


    private static RestClient restClient;

    public BookingServiceHealthIndicator(@Value("${booking.service.url}") String bookingServiceUrl) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(2));
        factory.setReadTimeout(Duration.ofSeconds(2));

        this.restClient = RestClient.builder()
                .baseUrl(bookingServiceUrl)
                .requestFactory(factory)
                .build();
    }

    @Override
    public Health health() {
        try{
            restClient.get()
                    .uri("/actuator/health")
                    .retrieve()
                    .toBodilessEntity();
            return Health.up().build();
        } catch (RestClientException e){
            log.error("Healthcheck towards booking service failed.");
            return Health.down().build();
        }
    }
}
