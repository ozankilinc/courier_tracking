package com.courier.tracking.service;

import com.courier.tracking.event.CourierLocationEvent;

public interface CourierLocationEventBusinessService {

    void handleCourierLocationEvent(CourierLocationEvent courierLocationEvent, String storeName);
}
