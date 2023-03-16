package com.courier.tracking.service.impl;


import com.courier.tracking.entity.CourierLeg;
import com.courier.tracking.mapper.CourierLegMapper;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.repository.CourierLegRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourierLegServiceImplTest {

    @Mock
    private CourierLegRepository repository;

    @Mock
    private CourierLegMapper mapper;

    @InjectMocks
    private CourierLegServiceImpl service;

    private static final String COURIER_ID = "courierId";

    @Test
    void shouldFindByCourierId() {
        // Given
        when(repository.findTopByCourierIdOrderByCreatedDateDesc(COURIER_ID)).thenReturn(Optional.empty());

        // When
        CourierLegDto actualDto = service.findByCourierId(COURIER_ID);

        // Then
        verifyNoInteractions(mapper);
        assertNull(actualDto);
    }

    @Test
    void shouldFindByCourierIdWithoutEmptyCourierLeg() {
        // Given
        CourierLeg courierLeg = new CourierLeg();
        CourierLegDto courierLegDto = new CourierLegDto();
        when(repository.findTopByCourierIdOrderByCreatedDateDesc(COURIER_ID)).thenReturn(Optional.of(courierLeg));
        when(mapper.mapToCourierLegDto(courierLeg)).thenReturn(courierLegDto);

        // When
        CourierLegDto actualDto = service.findByCourierId(COURIER_ID);

        // Then
        verify(mapper).mapToCourierLegDto(courierLeg);
        assertEquals(courierLegDto, actualDto);
    }

    @Test
    void shouldGetTotalTravelDistance() {
        //Given
        Double result = 46.0d;
        when(repository.getTotalTravelDistance(COURIER_ID)).thenReturn(result);

        // When
        Double actualResult = service.getTotalTravelDistance(COURIER_ID);

        // Then
        assertEquals(result, actualResult);
    }

}