package com.courier.tracking.mapper.impl;

import com.courier.tracking.entity.CourierLeg;
import com.courier.tracking.mapper.CourierLegMapper;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.dto.PointDto;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.util.NavUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourierLegMapperImpl implements CourierLegMapper {
    @Override
    public CourierLegDto mapToCourierLegDto(CourierLeg courierLeg) {
        PointDto currentPoint = PointDto.builder()
                .lat(courierLeg.getLat())
                .lng(courierLeg.getLng())
                .build();
        PointDto previousPoint = Optional.ofNullable(courierLeg.getPreviousLat())
                .map(lat -> PointDto.builder()
                        .lat(lat)
                        .lng(courierLeg.getPreviousLng())
                        .build()
                ).orElse(null);

        return CourierLegDto.builder()
                .courierId(courierLeg.getCourierId())
                .currentPoint(currentPoint)
                .currentTime(courierLeg.getTime())
                .previousPoint(previousPoint)
                .previousTime(Optional.ofNullable(courierLeg.getPreviousTime()).orElse(null))
                .distance(NavUtils.getDistance(previousPoint, currentPoint))
                .build();
    }

    @Override
    public CourierLeg mapToCourierLeg(CourierGeolocationRequest request, CourierLegDto dto) {
        CourierLeg courierLeg = mapToCourierLeg(request);
        courierLeg.setPreviousLat(dto.getCurrentPoint().getLat());
        courierLeg.setPreviousLng(dto.getCurrentPoint().getLng());
        courierLeg.setPreviousTime(dto.getCurrentTime());
        courierLeg.setDistance(NavUtils.getDistance(dto.getCurrentPoint(), request.getPoints()));
        return courierLeg;
    }

    @Override
    public CourierLeg mapToCourierLeg(CourierGeolocationRequest request) {
        return CourierLeg.builder()
                .courierId(request.getCourierId())
                .lat(request.getLat())
                .lng(request.getLng())
                .time(request.getTime())
                .build();
    }
}
