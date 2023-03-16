package com.courier.tracking.mapper.impl;

import com.courier.tracking.entity.CourierLeg;
import com.courier.tracking.model.dto.CourierDto;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourierLegMapperImplTest {

    @InjectMocks
    private CourierLegMapperImpl mapper;

    @Test
    void shouldMapToCourierLegDto() {
        // Given
        CourierLeg courierLeg = CourierLeg.builder()
                .time(LocalTime.of(12, 15, 12))
                .lng(0.0d)
                .lat(0.0d)
                .courierId("CourierId")
                .build();

        // When
        CourierLegDto courierLegDto = mapper.mapToCourierLegDto(courierLeg);

        // Then
        assertEquals(courierLeg.getCourierId(), courierLegDto.getCourierId());
        assertEquals(courierLeg.getTime(), courierLegDto.getCurrentTime());
        assertNotNull(courierLegDto.getCurrentPoint());
        assertNull(courierLegDto.getPreviousPoint());
    }

    @Test
    void shouldMapToCourierLegDtoWithPreviousData() {
        // Given
        CourierLeg courierLeg = CourierLeg.builder()
                .time(LocalTime.of(12, 15, 12))
                .lng(0.0d)
                .lat(0.0d)
                .courierId("CourierId")
                .previousTime(LocalTime.of(11, 11, 11))
                .previousLat(0.0d)
                .previousLng(0.0d)
                .build();

        // When
        CourierLegDto courierLegDto = mapper.mapToCourierLegDto(courierLeg);

        // Then
        assertEquals(courierLeg.getCourierId(), courierLegDto.getCourierId());
        assertEquals(courierLeg.getTime(), courierLegDto.getCurrentTime());
        assertNotNull(courierLegDto.getCurrentPoint());
        assertNotNull(courierLegDto.getPreviousPoint());
    }

    @Test
    void shouldMapToCourierLeg() {
        // Given
        CourierGeolocationRequest request = CourierGeolocationRequest.builder()
                .time(LocalTime.of(12, 12,12))
                .lng(0.0d)
                .lat(0.0d)
                .courier(CourierDto.builder().courierId("CourierId").build())
                .build();

        CourierLegDto dto = CourierLegDto.builder()
                .courierId("CourierId")
                .currentTime(LocalTime.of(11,11,11))
                .currentPoint(PointDto.builder()
                        .lng(0.0d)
                        .lat(0.0d)
                        .build())
                .build();

        // When
        CourierLeg courierLeg = mapper.mapToCourierLeg(request, dto);

        // Then
        assertEquals(request.getCourierId(), courierLeg.getCourierId());
        assertEquals(request.getTime(), courierLeg.getTime());
        assertEquals(request.getLat(), courierLeg.getLat());
        assertEquals(request.getLng(), courierLeg.getLng());
        assertEquals(dto.getCurrentTime(), courierLeg.getPreviousTime());
        assertEquals(dto.getCurrentPoint().getLat(), courierLeg.getLat());
        assertEquals(dto.getCurrentPoint().getLng(), courierLeg.getLng());
        assertNotNull(courierLeg.getDistance());
    }
}