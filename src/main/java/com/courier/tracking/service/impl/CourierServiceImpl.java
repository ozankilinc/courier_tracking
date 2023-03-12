package com.courier.tracking.service.impl;

import com.courier.tracking.entity.Courier;
import com.courier.tracking.repository.CourierRepository;
import com.courier.tracking.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    @Override
    public Courier saveCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    @Override
    public List<Courier> getAllCourier() {
        return courierRepository.findAll();
    }
}
