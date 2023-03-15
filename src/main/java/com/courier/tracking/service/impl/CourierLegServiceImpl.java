package com.courier.tracking.service.impl;

import com.courier.tracking.entity.CourierLeg;
import com.courier.tracking.mapper.CourierLegMapper;
import com.courier.tracking.model.constant.CourierTrackingConstants;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.repository.CourierLegRepository;
import com.courier.tracking.service.CourierLegService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CourierLegServiceImpl implements CourierLegService {

    private final CourierLegRepository courierLegRepository;
    private final CourierLegMapper courierLegMapper;


    @CachePut(value = CourierTrackingConstants.COURIER_LEG_CACHE, key = "#request.courierId")
    @Override
    public CourierLegDto saveCourierLeg(CourierGeolocationRequest request, CourierLegDto courierLegDto) {
        CourierLeg courierLeg = Optional.ofNullable(courierLegDto)
                .map(dto -> courierLegMapper.mapToCourierLeg(request, courierLegDto))
                .orElseGet(() -> courierLegMapper.mapToCourierLeg(request));
        return courierLegMapper.mapToCourierLegDto(courierLegRepository.save(courierLeg));
    }


    @Cacheable(value = CourierTrackingConstants.COURIER_LEG_CACHE, key = "#courierId")
    @Override
    public CourierLegDto findByCourierId(String courierId) {
        return courierLegRepository.findTopByCourierIdOrderByCreatedDateDesc(courierId)
                .map(courierLegMapper::mapToCourierLegDto)
                .orElse(null);
    }

    @Override
    public Double getTotalTravelDistance(String courierId) {
        return courierLegRepository.getTotalTravelDistance(courierId);
    }
}
