package com.courier.tracking.mapper;

import com.courier.tracking.entity.CourierLeg;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.request.CourierGeolocationRequest;

public interface CourierLegMapper {

    CourierLegDto mapToCourierLegDto(CourierLeg courierLeg);

    CourierLeg mapToCourierLeg(CourierGeolocationRequest request, CourierLegDto dto);

    CourierLeg mapToCourierLeg(CourierGeolocationRequest request);
}
