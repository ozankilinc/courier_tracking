package com.courier.tracking.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(columnList = "courier_id")})
public class CourierLeg extends BaseEntity {

    @Column(name = "courier_id")
    private String courierId;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "previous_lng")
    private Double previousLng;

    @Column(name = "previous_lat")
    private Double previousLat;

    @Column(name = "previous_time")
    private LocalTime previousTime;

    @Column(name = "distance")
    @Builder.Default
    private Double distance = 0.0d;
}
