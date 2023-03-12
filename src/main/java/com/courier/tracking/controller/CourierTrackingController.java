package com.courier.tracking.controller;

import com.courier.tracking.model.constant.CourierTrackingMessages;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.service.CourierBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courier")
public class CourierTrackingController {

    private final CourierBusinessService courierBusinessService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCourierGeolocations(@RequestBody List<CourierGeolocationRequest> request) {
        courierBusinessService.processCourierGeolocations(request);
        return ResponseEntity.ok(CourierTrackingMessages.COURIER_UPLOAD_MESSAGE);
    }
/*
    @PostMapping("/upload/one")
    public ResponseEntity<String> uploadCourierGeolocation(@RequestBody CourierGeolocationRequest request) {
        courierBusinessService.processCourierGeolocation(request);
        return ResponseEntity.ok(CourierTrackingMessages.COURIER_UPLOAD_MESSAGE);
    }

 */
}
