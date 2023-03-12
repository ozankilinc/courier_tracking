package com.courier.tracking.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    private String name;
    private Double lat;
    private Double lng;

    @JsonIgnore
    public PointDto getPoint() {
        return PointDto.builder()
                .lat(getLat())
                .lng(getLng())
                .build();
    }
}
