package com.courier.tracking.listener;


import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.service.CourierLocationEventBusinessService;
import com.courier.tracking.service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourierGeoLocationEventListenerTest {

    @Mock
    private StoreService service;

    @Mock
    private CourierLocationEventBusinessService businessService;

    @InjectMocks
    private CourierGeoLocationEventListener listener;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<CourierLocationEvent> courierLocationEventArgumentCaptor;

    @Test
    void shouldListenEvent() {
        // Given
        CourierLegDto dto = new CourierLegDto();
        PointDto currentPoint = PointDto.builder().lng(0.0d).lat(0.0).build();
        dto.setCurrentPoint(currentPoint);
        CourierLocationEvent event = CourierLocationEvent.builder().courierLegDto(dto).build();
        when(service.getNearestStoreByPoint(currentPoint)).thenReturn("Test1");

        //When
        listener.listenEvent(event);

        // Then
        verify(businessService).handleCourierLocationEvent(courierLocationEventArgumentCaptor.capture(), stringArgumentCaptor.capture());
        String capturedValue = stringArgumentCaptor.getValue();
        CourierLocationEvent capturedEvent = courierLocationEventArgumentCaptor.getValue();

        assertEquals(event, capturedEvent);
        assertEquals("Test1", capturedValue);
    }

}