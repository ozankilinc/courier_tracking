package com.courier.tracking.model.dto;

import lombok.Data;

import java.time.Duration;

@Data
public class CacheSpec {

    private String cacheName;
    private Duration cacheTtl;
}
