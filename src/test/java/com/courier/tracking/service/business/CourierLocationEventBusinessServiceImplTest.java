package com.courier.tracking.service.business;

import com.courier.tracking.config.EntranceRule;
import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.model.dto.StoreDto;
import com.courier.tracking.service.CourierEntranceLogService;
import com.courier.tracking.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierLocationEventBusinessServiceImplTest {

    @Mock
    private EntranceRule entranceRule;

    @Mock
    private StoreService storeService;

    @Mock
    private CourierEntranceLogService courierEntranceLogService;

    @InjectMocks
    private CourierLocationEventBusinessServiceImpl courierLocationEventBusinessService;

    private static final String STORE_NAME = "TestStore";

    @BeforeEach
    void setUp() {
        StoreDto storeDto = StoreDto.builder()
                .name(STORE_NAME)
                .lat(0.0)
                .lng(0.0)
                .build();
        when(storeService.getStoreByName(STORE_NAME)).thenReturn(storeDto);
        when(entranceRule.getRadius()).thenReturn(10.0d);
    }

    @Test
    void shouldHandleCourierLocationEventWhenPreviousPointIsNullAndIsInTheRadius() {
        // When
        CourierLegDto courierLegDto = CourierLegDto.builder()
                .courierId("CourierId")
                .currentTime(LocalTime.of(12, 12, 12))
                .distance(0.0d)
                .currentPoint(
                        PointDto.builder()
                                .lat(0.0d)
                                .lng(0.0d)
                                .build()
                )
                .build();
        CourierLocationEvent courierLocationEvent = CourierLocationEvent.builder()
                .courierLegDto(courierLegDto)
                .build();

        // When
        courierLocationEventBusinessService.handleCourierLocationEvent(courierLocationEvent, STORE_NAME);

        // Then
        verify(courierEntranceLogService).logCourierEntrance(courierLegDto, STORE_NAME);
    }

    @Test
    void shouldHandleCourierLocationEventWhenPreviousPointIsNotNullAndIsInTheRadiusAndTimeDiffIsSmallerThanOneMinute() {
        // When
        CourierLegDto courierLegDto = CourierLegDto.builder()
                .courierId("CourierId")
                .currentTime(LocalTime.of(12, 12, 12))
                .previousTime(LocalTime.of(12, 11,50))
                .distance(0.0d)
                .currentPoint(
                        PointDto.builder()
                                .lat(0.0d)
                                .lng(0.0d)
                                .build()
                )
                .previousPoint(
                        PointDto.builder()
                                .lat(0.0d)
                                .lng(0.0d)
                                .build()
                )
                .build();
        CourierLocationEvent courierLocationEvent = CourierLocationEvent.builder()
                .courierLegDto(courierLegDto)
                .build();
        when(entranceRule.getDuration()).thenReturn(Duration.ofMinutes(1));

        // When
        courierLocationEventBusinessService.handleCourierLocationEvent(courierLocationEvent, STORE_NAME);

        // Then
        verify(courierEntranceLogService).logCourierEntrance(courierLegDto, STORE_NAME);
    }

    @Test
    void shouldHandleCourierLocationEventWhenPreviousPointIsNotNullAndIsInTheRadiusAndTimeDiffIsGreaterThanOneMinute() {
        // When
        CourierLegDto courierLegDto = CourierLegDto.builder()
                .courierId("CourierId")
                .currentTime(LocalTime.of(12, 12, 12))
                .previousTime(LocalTime.of(12, 10,50))
                .distance(0.0d)
                .currentPoint(
                        PointDto.builder()
                                .lat(0.0d)
                                .lng(0.0d)
                                .build()
                )
                .previousPoint(
                        PointDto.builder()
                                .lat(0.0d)
                                .lng(0.0d)
                                .build()
                )
                .build();
        CourierLocationEvent courierLocationEvent = CourierLocationEvent.builder()
                .courierLegDto(courierLegDto)
                .build();
        when(entranceRule.getDuration()).thenReturn(Duration.ofMinutes(1));

        // When
        courierLocationEventBusinessService.handleCourierLocationEvent(courierLocationEvent, STORE_NAME);

        // Then
        verifyNoInteractions(courierEntranceLogService);
    }

    @Test
    void shouldNotLogWhenDistanceIsGreaterThanRadius() {
        // When
        CourierLegDto courierLegDto = CourierLegDto.builder()
                .courierId("CourierId")
                .currentTime(LocalTime.of(12, 12, 12))
                .distance(0.0d)
                .currentPoint(
                        PointDto.builder()
                                .lat(42.0)
                                .lng(29.0d)
                                .build()
                )
                .build();
        CourierLocationEvent courierLocationEvent = CourierLocationEvent.builder()
                .courierLegDto(courierLegDto)
                .build();

        // When
        courierLocationEventBusinessService.handleCourierLocationEvent(courierLocationEvent, STORE_NAME);

        // Then
        verifyNoInteractions(courierEntranceLogService);
    }

    @Test
    void shouldLogWhenPreviousPointOutOfRadiusAndCurrentPointInRadius() {
        // When
        CourierLegDto courierLegDto = CourierLegDto.builder()
                .courierId("CourierId")
                .currentTime(LocalTime.of(12, 12, 12))
                .previousTime(LocalTime.of(12, 10,50))
                .distance(10.0d)
                .currentPoint(
                        PointDto.builder()
                                .lat(0.0d)
                                .lng(0.0d)
                                .build()
                )
                .previousPoint(
                        PointDto.builder()
                                .lat(42.0d)
                                .lng(29.0d)
                                .build()
                )
                .build();
        CourierLocationEvent courierLocationEvent = CourierLocationEvent.builder()
                .courierLegDto(courierLegDto)
                .build();

        // When
        courierLocationEventBusinessService.handleCourierLocationEvent(courierLocationEvent, STORE_NAME);

        // Then
        verify(courierEntranceLogService).logCourierEntrance(courierLegDto, STORE_NAME);
    }
}