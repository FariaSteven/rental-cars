package com.rentalCars.rentalCars.domain.users.dto;

import com.rentalCars.rentalCars.domain.users.UserRoles;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank
         String login,
        @NotBlank
         String password,
        @NotBlank
         String email,
        @NotBlank
         String phone,
         Boolean hasCar,
         String planType,
         UserRoles role
) {
}
