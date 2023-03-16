package com.courier.tracking.model.request;

import com.courier.tracking.model.dto.CourierDto;
import com.courier.tracking.model.dto.PointDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourierGeolocationRequest extends BaseRequest {

    @NotNull(message = "Time Should Not Be Null")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;
    @Valid
    private CourierDto courier;

    @NotNull(message = "Latitude Should Not Be Null")
    @DecimalMin(value = "0.0", message = "Latitude Should Be Greater Than 0")
    private Double lat;

    @NotNull(message = "Longitude Should Not Be Null")
    @DecimalMin(value = "0.0", message = "Longitude Should Be Greater Than 0")
    private Double lng;

    @JsonIgnore
    public PointDto getPoints() {
        return PointDto.builder()
                .lat(getLat())
                .lng(getLng())
                .build();
    }

    public String getCourierId() {
        return getCourier().getCourierId();
    }
}
