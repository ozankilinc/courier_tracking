package com.courier.tracking.service.business;

import com.courier.tracking.config.EntranceRule;
import com.courier.tracking.event.CourierLocationEvent;
import com.courier.tracking.model.dto.CourierLegDto;
import com.courier.tracking.model.dto.StoreDto;
import com.courier.tracking.service.CourierEntranceLogService;
import com.courier.tracking.service.CourierLocationEventBusinessService;
import com.courier.tracking.service.StoreService;
import com.courier.tracking.util.NavUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourierLocationEventBusinessServiceImpl implements CourierLocationEventBusinessService {
    private final EntranceRule entranceRule;
    private final StoreService storeService;
    private final CourierEntranceLogService courierEntranceLogService;

    @Override
    public void handleCourierLocationEvent(CourierLocationEvent courierLocationEvent, String storeName) {
        CourierLegDto courierLegDto = courierLocationEvent.getCourierLegDto();
        StoreDto store = storeService.getStoreByName(storeName);
        if (NavUtils.getDistance(courierLegDto.getCurrentPoint(), store.getPoint()) <= entranceRule.getRadius()) {
            if (courierLegDto.getPreviousPoint() == null) {
                courierEntranceLogService.logCourierEntrance(courierLegDto, storeName);
                return;
            }
            if(NavUtils.getDistance(courierLegDto.getPreviousPoint(), store.getPoint()) <= entranceRule.getRadius()) {
                Duration duration = Duration.between(courierLegDto.getPreviousTime(), courierLegDto.getCurrentTime());
                if (duration.compareTo(entranceRule.getDuration()) < 0) {
                    courierEntranceLogService.logCourierEntrance(courierLegDto, storeName);
                }
                return;
            }
            courierEntranceLogService.logCourierEntrance(courierLegDto, storeName);
        }
    }
}
