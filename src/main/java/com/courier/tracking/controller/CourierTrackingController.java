package com.courier.tracking.controller;

import com.courier.tracking.model.constant.CourierTrackingConstants;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.service.CourierBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/courier")
@Tag(name = "Courier Tracking Service", description = "The Courier Tracking API")
public class CourierTrackingController {

    private final CourierBusinessService courierBusinessService;

    @Operation(summary = "Post a Geolocation Streams")
    @ApiResponse(responseCode = "200", description = "It is worked as async. Its response just gives message")
    @PostMapping("/geolocations")
    public ResponseEntity<String> uploadCourierGeolocations(@RequestBody @Valid List<CourierGeolocationRequest> request) {
        courierBusinessService.processCourierGeolocations(request);
        return ResponseEntity.ok(CourierTrackingConstants.COURIER_UPLOAD_MESSAGE);
    }

    @Operation(summary = "Post a Geolocation")
    @ApiResponse(responseCode = "200", description = "It is worked as async. Its response just gives message")
    @PostMapping("/geolocation")
    public ResponseEntity<String> uploadCourierGeolocation(@RequestBody @Valid CourierGeolocationRequest request) {
        courierBusinessService.processCourierGeolocation(request);
        return ResponseEntity.ok(CourierTrackingConstants.COURIER_UPLOAD_MESSAGE);
    }

    @Operation(summary = "Get Courier Total Distance By courierId")
    @ApiResponse(responseCode = "200", description = "It returns total distance of courier for courierId")
    @GetMapping("/{courierId}")
    public ResponseEntity<Double> getTotalTravelDistance(@PathVariable(name = "courierId") String courierId) {
        return ResponseEntity.ok(courierBusinessService.getTotalTravelDistance(courierId));
    }
}
