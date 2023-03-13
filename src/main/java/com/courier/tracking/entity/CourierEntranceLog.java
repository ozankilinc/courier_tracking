package com.courier.tracking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(columnList = "courier_id")})
public class CourierEntranceLog extends BaseEntity {

    @Column(name = "courier_id")
    private String courierId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "lat")
    private Double lat;

    @Override
    public String toString() {
        return "CourierEntranceLog{" +
                "courierId='" + courierId + '\'' +
                ", storeName='" + storeName + '\'' +
                "Point{" + '\'' +
                    ", lng=" + lng +
                    ", lat=" + lat +
                '}';
    }
}
