package com.courier.tracking.mapper.impl;

import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.mapper.CourierLocationEventMapper;
import com.courier.tracking.model.dto.CourierLegDto;
import org.springframework.stereotype.Component;

@Component
public class CourierLocationEventMapperImpl implements CourierLocationEventMapper {
    @Override
    public CourierLocationEvent mapToCourierLocationEvent(CourierLegDto courierLegDto) {
        return CourierLocationEvent.builder()
                .courierLegDto(courierLegDto)
                .build();
    }
}
