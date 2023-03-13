package com.courier.tracking.mapper;

import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.model.dto.CourierLegDto;

public interface CourierLocationEventMapper {

    CourierLocationEvent mapToCourierLocationEvent(CourierLegDto courierLegDto);
}
