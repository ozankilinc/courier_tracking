package com.courier.tracking.service.impl;

import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.model.dto.StoreDto;
import com.courier.tracking.repository.StoreRepository;
import com.courier.tracking.service.StoreService;
import com.courier.tracking.util.NavUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public StoreDto getStoreByName(String name) {
        return storeRepository.getStoreByName(name);
    }

    @Override
    public String getNearestStoreByPoint(PointDto point) {
        return storeRepository.getAllStores().stream()
                .peek(storeDto -> {
                    Double nearestDistance = NavUtils.getDistance(point, storeDto.getPoint());
                    storeDto.setNearestDistance(nearestDistance);
                })
                .min(Comparator.comparingDouble(StoreDto::getNearestDistance))
                .map(StoreDto::getName)
                .orElse(null);
    }

}
