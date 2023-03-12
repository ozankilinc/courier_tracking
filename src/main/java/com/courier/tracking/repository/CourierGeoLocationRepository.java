package com.courier.tracking.repository;

import com.courier.tracking.entity.CourierGeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierGeoLocationRepository extends JpaRepository<CourierGeoLocation, Long> {
}
