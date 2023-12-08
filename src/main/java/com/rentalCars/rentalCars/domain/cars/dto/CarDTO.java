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
         String doors,
         @NotBlank
         String gearbox,
         @NotBlank
         String fuel,
         @NotBlank
         String dimensions,
         @NotBlank
         String trunk,
         @NotBlank
         String avgConsumptionCity,
         @NotBlank
         String avgConsumptionRoad,
         @NotBlank
         String power,
         Boolean isRented,
         @NotBlank
         String rentedBy,
         @NotNull
         BigDecimal price,
         @NotBlank
         String airbag,
         @NotBlank
         String brake
) {
}
