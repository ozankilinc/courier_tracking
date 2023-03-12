package com.courier.tracking.service;

import com.courier.tracking.model.dto.StoreDto;

import java.util.List;

public interface StoreService {

    List<StoreDto> getAllStores();

    List<String> getAllStoreNames();

    StoreDto getStoreByName(String name);
}
