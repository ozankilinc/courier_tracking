package com.courier.tracking.controller;

import com.courier.tracking.model.constant.CourierTrackingConstants;
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
        return ResponseEntity.ok(CourierTrackingConstants.COURIER_UPLOAD_MESSAGE);
    }

    @PostMapping("/upload/one")
    public ResponseEntity<String> uploadCourierGeolocation(@RequestBody CourierGeolocationRequest request) {
        courierBusinessService.processCourierGeolocation(request);
        return ResponseEntity.ok(CourierTrackingConstants.COURIER_UPLOAD_MESSAGE);
    }

    @GetMapping("/{courierId}")
    public ResponseEntity<Double> getTotalTravelDistance(@PathVariable(name = "courierId") String courierId) {
        return ResponseEntity.ok(courierBusinessService.getTotalTravelDistance(courierId));
    }
}
