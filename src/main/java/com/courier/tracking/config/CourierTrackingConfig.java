package com.courier.tracking.config;

import com.courier.tracking.repository.StoreRepository;
import com.courier.tracking.repository.impl.StoreRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class CourierTrackingConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public StoreRepository storeRepository() {
        return StoreRepositoryImpl.getInstance(objectMapper);
    }
}
