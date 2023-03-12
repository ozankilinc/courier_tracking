package com.courier.tracking.mapper;

import com.courier.tracking.event.CourierGeoLocationEvent;
import com.courier.tracking.model.request.CourierGeolocationRequest;

import java.util.List;

public interface CourierGeoLocationEventMapper {

    List<CourierGeoLocationEvent> mapToList(List<CourierGeolocationRequest> requestList);
}
