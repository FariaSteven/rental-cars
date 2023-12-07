package com.rentalCars.rentalCars.repositories;

import com.rentalCars.rentalCars.domain.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, String> {
}
