package com.courier.tracking.event;

import com.courier.tracking.model.dto.CourierLegDto;
import lombok.ToString;

@ToString
public class CourierLocationEvent {

    private CourierLegDto courierLegDto;

    private CourierLocationEvent() {
    }

    public CourierLegDto getCourierLegDto() {
        return courierLegDto;
    }

    public static CourierLocationEventBuilder builder() {
        return new CourierLocationEventBuilder();
    }

    public static class CourierLocationEventBuilder {
        private final CourierLocationEvent courierLocationEvent;

        public CourierLocationEventBuilder() {
            this.courierLocationEvent = new CourierLocationEvent();
        }

        public CourierLocationEventBuilder courierLegDto(CourierLegDto courierLegDto) {
            this.courierLocationEvent.courierLegDto = courierLegDto;
            return this;
        }

        public CourierLocationEvent build() {
            return this.courierLocationEvent;
        }
    }
}
