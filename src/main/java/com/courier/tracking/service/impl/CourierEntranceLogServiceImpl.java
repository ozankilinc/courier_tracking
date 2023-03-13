package com.courier.tracking.service.impl;

import com.courier.tracking.entity.CourierEntranceLog;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.repository.CourierEntranceLogRepository;
import com.courier.tracking.service.CourierEntranceLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourierEntranceLogServiceImpl implements CourierEntranceLogService {

    private final CourierEntranceLogRepository courierEntranceLogRepository;

    @Async
    @Override
    public void logCourierEntrance(CourierLegDto dto, String storeName) {
        CourierEntranceLog courierEntranceLog = courierEntranceLogRepository.save(buildCourierEntranceLog(dto, storeName));
        log.info(courierEntranceLog.toString());
    }

    private CourierEntranceLog buildCourierEntranceLog(CourierLegDto dto, String storeName) {
        return CourierEntranceLog.builder()
                .courierId(dto.getCourierId())
                .lat(dto.getCurrentPoint().getLat())
                .lng(dto.getCurrentPoint().getLng())
                .storeName(storeName)
                .build();
    }
}
