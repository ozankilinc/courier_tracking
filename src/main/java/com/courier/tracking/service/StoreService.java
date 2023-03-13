package com.courier.tracking.service;

import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.model.dto.StoreDto;

import java.util.List;

public interface StoreService {

    List<StoreDto> getAllStores();

    List<String> getAllStoreNames();

    StoreDto getStoreByName(String name);

    String getNearestStoreByPoint(PointDto point);

    List<String> getStoreNamesByPoint(PointDto point);
}
