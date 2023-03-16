package com.courier.tracking.publisher.impl;

import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.dto.PointDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierGeoLocationEventPublisherTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private CourierGeoLocationEventPublisher courierGeoLocationEventPublisher;

    @Test
    void shouldPublishEvent() {
        // Given
        CourierLegDto dto = new CourierLegDto();
        PointDto currentPoint = PointDto.builder().lng(0.0d).lat(0.0).build();
        dto.setCurrentPoint(currentPoint);
        CourierLocationEvent event = CourierLocationEvent.builder().courierLegDto(dto).build();

        // When
        courierGeoLocationEventPublisher.publishEvent(event);

        // Then
        verify(applicationEventPublisher).publishEvent(event);
    }
}