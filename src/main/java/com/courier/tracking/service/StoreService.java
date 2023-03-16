package com.courier.tracking.service;

import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.model.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto getStoreByName(String name);

    String getNearestStoreByPoint(PointDto point);
}
