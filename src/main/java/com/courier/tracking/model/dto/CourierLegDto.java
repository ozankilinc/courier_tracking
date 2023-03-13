package com.courier.tracking.model.dto;


import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierLegDto implements Serializable {

    private String courierId;
    private PointDto currentPoint;
    private LocalTime currentTime;
    private PointDto previousPoint;
    private LocalTime previousTime;
    private Double distance;
}
