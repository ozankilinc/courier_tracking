package com.courier.tracking.repository;

import com.courier.tracking.model.dto.StoreDto;

import java.util.List;

public interface StoreRepository {

    List<StoreDto> getAllStores();

    List<String> getAllStoreNames();

    StoreDto getStoreByName(String name);
}
