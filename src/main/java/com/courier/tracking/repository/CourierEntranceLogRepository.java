package com.courier.tracking.repository;

import com.courier.tracking.entity.CourierEntranceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierEntranceLogRepository extends JpaRepository<CourierEntranceLog, Long> {
}
