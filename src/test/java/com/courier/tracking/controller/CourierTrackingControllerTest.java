package com.courier.tracking.controller;

import com.courier.tracking.model.dto.CourierDto;
import com.courier.tracking.model.request.CourierGeolocationRequest;
import com.courier.tracking.service.CourierBusinessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CourierTrackingController.class)
class CourierTrackingControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @MockBean
    private CourierBusinessService courierBusinessService;

    @Captor
    private ArgumentCaptor<List<CourierGeolocationRequest>> listArgumentCaptor;

    @Captor
    private ArgumentCaptor<CourierGeolocationRequest> argumentCaptor;

    private static final String COURIER_ID = "courierId";

    private List<CourierGeolocationRequest> courierGeolocationRequests;
    private CourierGeolocationRequest courierGeolocationRequest;

    @BeforeEach
    void setUp() {
        courierGeolocationRequests = new ArrayList<>();
        courierGeolocationRequest = CourierGeolocationRequest.builder()
                .courier(CourierDto.builder()
                        .courierId(COURIER_ID)
                        .build())
                .lat(40.567d)
                .lng(34.56)
                .time(LocalTime.of(12, 30, 23))
                .build();
        courierGeolocationRequests.add(courierGeolocationRequest);
    }

    @Test
    void shouldUploadCourierGeolocations() throws Exception {
        // Given
        doNothing().when(courierBusinessService).processCourierGeolocations(courierGeolocationRequests);

        // When
        mockMvc.perform(post("/api/v1/courier/geolocations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courierGeolocationRequests)))
                .andExpect(status().isOk());

        // Then
        verify(courierBusinessService).processCourierGeolocations(listArgumentCaptor.capture());
        List<CourierGeolocationRequest> capturedList = listArgumentCaptor.getValue();
        assertEquals(courierGeolocationRequests.get(0).toString(), capturedList.get(0).toString());
    }

    @Test
    void shouldUploadCourierGeolocation() throws Exception {
        // Given
        doNothing().when(courierBusinessService).processCourierGeolocation(courierGeolocationRequest);

        // When
        mockMvc.perform(post("/api/v1/courier/geolocation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courierGeolocationRequest)))
                .andExpect(status().isOk());

        // Then
        verify(courierBusinessService).processCourierGeolocation(argumentCaptor.capture());
        CourierGeolocationRequest capturedCourierGeolocation = argumentCaptor.getValue();
        assertEquals(courierGeolocationRequest.toString(), capturedCourierGeolocation.toString());
    }

    @Test
    void shouldGetTotalTravelDistance() throws Exception {
        // Given
        Double RESULT = 45.0;
        when(courierBusinessService.getTotalTravelDistance(COURIER_ID)).thenReturn(RESULT);

        // When
        mockMvc.perform(get("/api/v1/courier/{courierId}", COURIER_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(RESULT)));
    }
}