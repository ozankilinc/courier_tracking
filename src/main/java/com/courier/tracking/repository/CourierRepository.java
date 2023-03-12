package com.courier.tracking.repository;

import com.courier.tracking.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Long> {

    List<Courier> findByName(String name);
}
