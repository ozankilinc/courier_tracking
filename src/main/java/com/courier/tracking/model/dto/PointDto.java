package com.courier.tracking.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PointDto {

    private Double lat;
    private Double lng;
}
