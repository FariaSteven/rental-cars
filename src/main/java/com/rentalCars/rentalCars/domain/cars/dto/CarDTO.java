package com.rentalCars.rentalCars.domain.cars.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarDTO(
         @NotBlank
         String model,
         @NotBlank
         String engine,
         @NotNull
         Number door,
         Boolean gearbox,
         @NotBlank
         String fuel,
         @NotBlank
         String dimensions,
         @NotNull
         Number trunk,
         @NotNull
         Number avgConsumptionCity,
         @NotNull
         Number avgConsumptionRoad,
         @NotNull
         Number power,
         Boolean isRented,
         @NotNull
         BigDecimal price
) {
}
