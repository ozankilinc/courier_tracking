package com.courier.tracking.event;

import com.courier.tracking.model.dto.CourierDto;
import com.courier.tracking.model.dto.PointDto;
import lombok.ToString;

import java.time.LocalTime;

@ToString
public class CourierGeoLocationEvent {

    private CourierDto courier;
    private PointDto startPoint;
    private LocalTime startTime;
    private PointDto nextPoint;
    private LocalTime nextTime;
    private Double distance;
    private String storeType;
    private Long sortId;
    private boolean isLast;

    private CourierGeoLocationEvent() {
    }

    public CourierDto getCourier() {
        return courier;
    }

    public PointDto getStartPoint() {
        return startPoint;
    }

    public PointDto getNextPoint() {
        return nextPoint;
    }

    public Double getDistance() {
        return distance;
    }

    public String getStoreType() {
        return storeType;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getNextTime() {
        return nextTime;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public static CourierGeoLocationEventBuilder builder() {
        return new CourierGeoLocationEventBuilder();
    }

    public static class CourierGeoLocationEventBuilder {
        private final CourierGeoLocationEvent courierGeoLocationEvent;

        public CourierGeoLocationEventBuilder() {
            this.courierGeoLocationEvent = new CourierGeoLocationEvent();
        }

        public CourierGeoLocationEventBuilder courier(CourierDto courier) {
            this.courierGeoLocationEvent.courier = courier;
            return this;
        }

        public CourierGeoLocationEventBuilder startPoint(PointDto startPoint) {
            this.courierGeoLocationEvent.startPoint = startPoint;
            return this;
        }

        public CourierGeoLocationEventBuilder startTime(LocalTime startTime) {
            this.courierGeoLocationEvent.startTime = startTime;
            return this;
        }

        public CourierGeoLocationEventBuilder nextPoint(PointDto nextPoint) {
            this.courierGeoLocationEvent.nextPoint = nextPoint;
            return this;
        }

        public CourierGeoLocationEventBuilder nextTime(LocalTime nextTime) {
            this.courierGeoLocationEvent.nextTime = nextTime;
            return this;
        }

        public CourierGeoLocationEventBuilder distance(Double distance) {
            this.courierGeoLocationEvent.distance = distance;
            return this;
        }

        public CourierGeoLocationEventBuilder storeType(String storeType) {
            this.courierGeoLocationEvent.storeType = storeType;
            return this;
        }

        public CourierGeoLocationEventBuilder sortId(Long sortId) {
            this.courierGeoLocationEvent.sortId = sortId;
            return this;
        }

        public CourierGeoLocationEvent build() {
            return this.courierGeoLocationEvent;
        }
    }
}
