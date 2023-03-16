package com.courier.tracking.mapper.impl;

import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.model.dto.CourierLegDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CourierLocationEventMapperImplTest {

    @InjectMocks
    private CourierLocationEventMapperImpl mapper;

    @Test
    void shouldMapToCourierLocationEvent() {
        // Given
        CourierLegDto courierLegDto = new CourierLegDto();

        // When
        CourierLocationEvent courierLocationEvent = mapper.mapToCourierLocationEvent(courierLegDto);

        // Then
        assertNotNull(courierLocationEvent.getCourierLegDto());
        assertEquals(courierLegDto, courierLocationEvent.getCourierLegDto());
    }

}