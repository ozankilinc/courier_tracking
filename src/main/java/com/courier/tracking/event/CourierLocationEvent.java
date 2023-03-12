package com.courier.tracking.event;

import com.courier.tracking.entity.CourierGeoLocation;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourierLocationEvent {

    private CourierGeoLocation courierGeoLocation;
}
