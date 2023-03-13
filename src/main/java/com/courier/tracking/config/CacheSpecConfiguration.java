package com.courier.tracking.config;

import com.courier.tracking.model.dto.CacheSpec;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@Data
@ConfigurationProperties("courier.tracking.cache")
public class CacheSpecConfiguration {

    private Map<String, CacheSpec> specs;
}
