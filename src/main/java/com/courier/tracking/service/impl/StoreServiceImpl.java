package com.courier.tracking.service.impl;

import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.model.dto.StoreDto;
import com.courier.tracking.repository.StoreRepository;
import com.courier.tracking.service.StoreService;
import com.courier.tracking.util.NavUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public List<StoreDto> getAllStores() {
        return storeRepository.getAllStores();
    }

    @Override
    public List<String> getAllStoreNames() {
        return storeRepository.getAllStoreNames();
    }

    @Override
    public StoreDto getStoreByName(String name) {
        return storeRepository.getStoreByName(name);
    }

    @Override
    public List<String> getStoreNamesByPoint(PointDto point) {
        return storeRepository.getAllStores().stream()
                .filter(store -> NavUtils.getDistance(point, store.getPoint()) <= 100)
                .map(StoreDto::getName)
                .collect(Collectors.toList());
    }


}
