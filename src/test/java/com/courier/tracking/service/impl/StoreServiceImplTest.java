package com.courier.tracking.service.impl;

import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.model.dto.StoreDto;
import com.courier.tracking.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreServiceImpl storeService;

    @Test
    void shouldGetStoreByName() {
        // Given
        StoreDto storeDto = new StoreDto();
        when(storeRepository.getStoreByName("Test")).thenReturn(storeDto);

        // When
        StoreDto actualStore = storeService.getStoreByName("Test");

        // Then
        assertEquals(storeDto, actualStore);
    }

    @Test
    void shouldGetNearestStoreByPoint() {
        // Given
        PointDto pointDto = new PointDto(0.0d, 0.0d);
        List<StoreDto> storeDtoList = List.of(
                StoreDto.builder().lat(1.0d).lng(1.0d).name("Test3").build(),
                StoreDto.builder().lat(0.0d).lng(0.0d).name("Test1").build()
        );
        when(storeRepository.getAllStores()).thenReturn(storeDtoList);

        // When
        String nearestStoreDto = storeService.getNearestStoreByPoint(pointDto);

        //Then
        assertEquals("Test1", nearestStoreDto);

    }
}