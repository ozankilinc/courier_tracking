package com.courier.tracking.repository.impl;

import com.courier.tracking.model.dto.StoreDto;
import com.courier.tracking.repository.StoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class StoreRepositoryImpl implements StoreRepository {

    private static StoreRepositoryImpl INSTANCE;
    private static final String STORES_JSON = "stores.json";

    private static final List<StoreDto> storeDtoList = new ArrayList<>();

    private StoreRepositoryImpl(ObjectMapper objectMapper) {
        storeDtoList.addAll(loadStoreDtoList(objectMapper, getClass()));
    }

    public static StoreRepositoryImpl getInstance(ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new StoreRepositoryImpl(objectMapper);
        }
        return INSTANCE;
    }

    private List<StoreDto> loadStoreDtoList(ObjectMapper objectMapper, Class<? extends StoreRepositoryImpl> aClass) {
        try {
            InputStream inputStream = aClass.getClassLoader().getResourceAsStream(STORES_JSON);
            InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String storesJson = bufferedReader.lines().collect(Collectors.joining());
            return Arrays.stream(objectMapper.readValue(storesJson, StoreDto[].class)).toList();

        }catch (Exception e) {
            log.error("Exception Occurred While reading stores from json file", e);
            return Collections.emptyList();
        }

    }

    @Override
    public List<StoreDto> getAllStores() {
        return storeDtoList;
    }

    @Override
    public List<String> getAllStoreNames() {
        return storeDtoList.stream().map(StoreDto::getName).collect(Collectors.toList());
    }

    @Override
    public StoreDto getStoreByName(String name) {
        return storeDtoList.stream()
                .filter(storeDto -> storeDto.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
