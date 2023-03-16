package com.courier.tracking.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourierDto {

    @NotBlank(message = "Courier Id Should Not Blank")
    private String courierId;
    private String courierName;


}
