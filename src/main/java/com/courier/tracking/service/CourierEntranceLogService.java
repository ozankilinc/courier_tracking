package com.courier.tracking.service;


import com.courier.tracking.model.dto.CourierLegDto;

public interface CourierEntranceLogService {

    void logCourierEntrance(CourierLegDto dto, String storeName);
}
