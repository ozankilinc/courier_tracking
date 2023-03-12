package com.courier.tracking.service;

import com.courier.tracking.entity.Courier;

import java.util.List;

public interface CourierService {

    Courier saveCourier(Courier courier);

    List<Courier> getAllCourier();
}
