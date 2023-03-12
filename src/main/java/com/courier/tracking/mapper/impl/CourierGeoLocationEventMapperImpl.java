package com.courier.tracking.mapper.impl;

import com.courier.tracking.event.CourierGeoLocationEvent;
import com.courier.tracking.mapper.CourierGeoLocationEventMapper;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.repository.StoreRepository;
import com.courier.tracking.util.NavUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourierGeoLocationEventMapperImpl implements CourierGeoLocationEventMapper {

    private final StoreRepository storeRepository;
    private final AtomicLong atomicLong = new AtomicLong(0);


    @Override
    public List<CourierGeoLocationEvent> mapToList(List<CourierGeolocationRequest> requestList) {
        Map<String, List<CourierGeolocationRequest>> groupedCourierGeolocationRequestByCourierId = getGroupingCourierGeolocationRequest(requestList);
        clearAtomicLong();
        List<String> allStoreNames = storeRepository.getAllStoreNames();
        return groupedCourierGeolocationRequestByCourierId.entrySet()
                .stream()
                .map(entry -> mapToListByEntrySet(entry, allStoreNames))
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(CourierGeoLocationEvent::getSortId))
                .collect(Collectors.toList());
    }

    private List<CourierGeoLocationEvent> mapToListByEntrySet(Map.Entry<String, List<CourierGeolocationRequest>> entry, List<String> storeNames) {
        List<CourierGeolocationRequest> sortedList = entry.getValue().stream()
                .sorted(Comparator.comparing(CourierGeolocationRequest::getSortId))
                .collect(Collectors.toList());
        return storeNames.stream()
                .map(storeName -> build(storeName, sortedList))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<CourierGeoLocationEvent> build(String storeName, List<CourierGeolocationRequest> value) {
        List<CourierGeoLocationEvent> eventList = new ArrayList<>();
        for (int i = 0; i<(value.size() -1); i++) {
            CourierGeoLocationEvent courierGeoLocationEvent = buildCourierGeoLocationEvent(value.get(i), value.get(i + 1));
            courierGeoLocationEvent.setStoreType(storeName);
            if (i == value.size() - 2) {
                courierGeoLocationEvent.setLast(true);
            }
            eventList.add(courierGeoLocationEvent);
        }
        return eventList;
    }

    private CourierGeoLocationEvent buildCourierGeoLocationEvent(CourierGeolocationRequest c1, CourierGeolocationRequest c2) {
        return CourierGeoLocationEvent.builder()
                .courier(c1.getCourier())
                .startPoint(c1.getPoints())
                .startTime(c1.getTime())
                .nextPoint(c2.getPoints())
                .nextTime(c2.getTime())
                .sortId(c1.getSortId())
                .distance(NavUtils.getDistance(c1.getPoints(), c2.getPoints()))
                .build();
    }


    private Map<String, List<CourierGeolocationRequest>> getGroupingCourierGeolocationRequest(List<CourierGeolocationRequest> requestList) {
         return requestList.stream()
                .peek(courierGeolocationRequest -> {
                    Long sortId = atomicLong.incrementAndGet();
                    courierGeolocationRequest.setSortId(sortId);
                })
                .collect(Collectors.groupingBy(CourierGeolocationRequest::getCourierId));
    }

    private void clearAtomicLong() {
        atomicLong.set(0);
    }
}
