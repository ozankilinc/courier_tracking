package com.courier.tracking.model.request;

import com.courier.tracking.model.dto.CourierDto;
import com.courier.tracking.model.dto.PointDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourierGeolocationRequest extends BaseRequest {

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
    private CourierDto courier;
    private Double lat;
    private Double lng;
    @JsonIgnore
    private Long sortId;

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
