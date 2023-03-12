package com.courier.tracking.service.impl;

import com.courier.tracking.model.dto.StoreDto;
import com.courier.tracking.repository.StoreRepository;
import com.courier.tracking.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
