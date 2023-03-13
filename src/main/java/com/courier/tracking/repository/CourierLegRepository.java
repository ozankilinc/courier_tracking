package com.courier.tracking.repository;

import com.courier.tracking.entity.CourierLeg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourierLegRepository extends JpaRepository<CourierLeg, Long> {

    Optional<CourierLeg> findByCourierId(String courierId);

    @Query("SELECT SUM(c.distance) FROM CourierLeg c WHERE c.courierId =  ?1")
    Double getTotalTravelDistance(String courierId);

}
