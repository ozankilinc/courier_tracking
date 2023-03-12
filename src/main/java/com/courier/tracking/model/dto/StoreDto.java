package com.courier.tracking.model.dto;

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
}
