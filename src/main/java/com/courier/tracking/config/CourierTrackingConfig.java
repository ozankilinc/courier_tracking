package com.courier.tracking.config;

import com.courier.tracking.repository.StoreRepository;
import com.courier.tracking.repository.impl.StoreRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableAsync
@EnableCaching
@RequiredArgsConstructor
public class CourierTrackingConfig {

    private final ObjectMapper objectMapper;
    private final CacheSpecConfiguration cacheSpecConfiguration;

    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caffeineCacheList = cacheSpecConfiguration.getSpecs()
                .values()
                .stream()
                .map(cacheSpec -> buildCache(cacheSpec.getCacheName(), cacheSpec.getCacheTtl()))
                .collect(Collectors.toList());
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(caffeineCacheList);
        return simpleCacheManager;

    }

    private CaffeineCache buildCache(String cacheName, Duration cacheTtl) {
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .expireAfterWrite(cacheTtl);
        return new CaffeineCache(cacheName, caffeine.build());
    }

    @Bean
    public StoreRepository storeRepository() {
        return StoreRepositoryImpl.getInstance(objectMapper);
    }
}
