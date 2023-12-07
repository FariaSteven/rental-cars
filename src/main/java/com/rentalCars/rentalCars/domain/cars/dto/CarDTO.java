package com.rentalCars.rentalCars.domain.cars.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarDTO(
         @NotBlank
         String model,
         @NotBlank
         String color,
         @NotBlank
         String engine,
         @NotNull
         Number doors,
         @NotBlank
         String gearbox,
         @NotBlank
         String fuel,
         @NotBlank
         String dimensions,
         @NotNull
         Number trunk,
         @NotNull
         String avgConsumptionCity,
         @NotNull
         String avgConsumptionRoad,
         @NotNull
         Number power,
         Boolean isRented,
         @NotBlank
         String rentedBy,
         @NotNull
         BigDecimal price,
         @NotNull
         Number airbag,
         @NotBlank
         String brake
) {
}
