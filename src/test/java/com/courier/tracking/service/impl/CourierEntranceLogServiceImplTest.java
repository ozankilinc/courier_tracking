package com.courier.tracking.service.impl;

import com.courier.tracking.entity.CourierEntranceLog;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.repository.CourierEntranceLogRepository;
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
class CourierEntranceLogServiceImplTest {

    @Mock
    private CourierEntranceLogRepository repository;

    @InjectMocks
    private CourierEntranceLogServiceImpl courierEntranceLogService;

    @Captor
    private ArgumentCaptor<CourierEntranceLog> argumentCaptor;

    @Test
    void shouldLogCourierEntrance() {
        // When
        CourierLegDto dto = new CourierLegDto();
        dto.setCourierId("CourierId");
        dto.setCurrentPoint(new PointDto(0.0d, 0.0d));
        CourierEntranceLog entranceLog = new CourierEntranceLog();
        entranceLog.setCourierId("CourierId");
        when(repository.save(any(CourierEntranceLog.class))).thenReturn(entranceLog);

        // When
        courierEntranceLogService.logCourierEntrance(dto, "Test1");

        // Then
        verify(repository).save(argumentCaptor.capture());
        CourierEntranceLog capturedValue = argumentCaptor.getValue();
        assertEquals("Test1", capturedValue.getStoreName());
        assertEquals(dto.getCourierId(), capturedValue.getCourierId());


    }
}