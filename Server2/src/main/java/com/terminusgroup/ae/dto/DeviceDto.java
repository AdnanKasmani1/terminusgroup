package com.terminusgroup.ae.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeviceDto {
    @NotNull(message = "imei is required")
    @NotBlank(message = "imei must not be null")
    String imei;
    @NotNull(message = "sim is required")
    @NotBlank(message = "sim must not be null")
    String sim;
}
