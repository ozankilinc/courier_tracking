package com.courier.tracking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CourierGeoLocation {

    // : TODO burada uniqulik sağlanacak

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "courier_id")
    private String courierId;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP WITH ZONE")
    @CreationTimestamp
    private ZonedDateTime createdDate;
}
