package com.courier.tracking.service.business;

import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.mapper.CourierLocationEventMapper;
import com.courier.tracking.model.dto.CourierDto;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.publisher.impl.CourierGeoLocationEventPublisher;
import com.courier.tracking.service.CourierLegService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourierBusinessServiceImplTest {

    @Mock
    private CourierLegService courierLegService;

    @Mock
    private CourierLocationEventMapper courierLocationEventMapper;

    @Mock
    private CourierGeoLocationEventPublisher eventPublisher;

    @InjectMocks
    private CourierBusinessServiceImpl courierBusinessService;

    private List<CourierGeolocationRequest> courierGeolocationRequests;
    private CourierGeolocationRequest courierGeolocationRequest;

    @BeforeEach
    void setUp() {
        courierGeolocationRequests = new ArrayList<>();
        courierGeolocationRequest = CourierGeolocationRequest.builder()
                .courier(CourierDto.builder()
                        .courierId("CourierId")
                        .build())
                .lat(40.567d)
                .lng(34.56)
                .time(LocalTime.of(12, 30, 23))
                .build();
        courierGeolocationRequests.add(courierGeolocationRequest);
    }

    @Test
    void shouldProcessCourierGeolocations() {
        // Given
        CourierLegDto courierLegDto = new CourierLegDto();
        CourierLocationEvent courierLocationEvent = CourierLocationEvent.builder().build();
        when(courierLegService.findByCourierId(courierGeolocationRequest.getCourierId())).thenReturn(courierLegDto);
        when(courierLegService.saveCourierLeg(courierGeolocationRequest, courierLegDto)).thenReturn(courierLegDto);
        when(courierLocationEventMapper.mapToCourierLocationEvent(courierLegDto)).thenReturn(courierLocationEvent);
        doNothing().when(eventPublisher).publishEvent(courierLocationEvent);

        // When
        courierBusinessService.processCourierGeolocations(courierGeolocationRequests);

        // Then
        verify(eventPublisher).publishEvent(courierLocationEvent);
        verify(courierLegService).findByCourierId(courierGeolocationRequest.getCourierId());
        verify(courierLegService).saveCourierLeg(courierGeolocationRequest, courierLegDto);
    }

    @Test
    void shouldProcessCourierGeolocation() {
        // Given
        CourierLegDto courierLegDto = new CourierLegDto();
        CourierLocationEvent courierLocationEvent = CourierLocationEvent.builder().build();
        when(courierLegService.findByCourierId(courierGeolocationRequest.getCourierId())).thenReturn(courierLegDto);
        when(courierLegService.saveCourierLeg(courierGeolocationRequest, courierLegDto)).thenReturn(courierLegDto);
        when(courierLocationEventMapper.mapToCourierLocationEvent(courierLegDto)).thenReturn(courierLocationEvent);
        doNothing().when(eventPublisher).publishEvent(courierLocationEvent);

        // When
        courierBusinessService.processCourierGeolocation(courierGeolocationRequest);

        // Then
        verify(eventPublisher).publishEvent(courierLocationEvent);
        verify(courierLegService).findByCourierId(courierGeolocationRequest.getCourierId());
        verify(courierLegService).saveCourierLeg(courierGeolocationRequest, courierLegDto);
    }

    @Test
    void shouldGetTotalTravelDistance() {
        // Given
        Double result = 12.34d;
        when(courierLegService.getTotalTravelDistance("courierId")).thenReturn(result);

        // When
        Double actualResult = courierBusinessService.getTotalTravelDistance("courierId");

        // Then
        assertEquals(result, actualResult);
    }
}