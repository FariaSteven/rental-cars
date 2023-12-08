package com.rentalCars.rentalCars.controllers;

import com.rentalCars.rentalCars.domain.cars.Car;
import com.rentalCars.rentalCars.domain.cars.dto.CarDTO;
import com.rentalCars.rentalCars.repositories.CarRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
@SecurityRequirement(name = "bearer-key")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @GetMapping
    public ResponseEntity<Page<Car>> getAllCars(@PageableDefault(size = 10) Pageable pagination) {
        var page = carRepository.findAll(pagination);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody @Valid CarDTO carDTO) {
        Car newCar = new Car(carDTO);
        System.out.println(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(carRepository.save(newCar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable(value = "id") String id){
        Optional<Car> carO = carRepository.findById(id);
        if(carO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(carO.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value = "id") String id, @RequestBody @Valid CarDTO carDTO) {
        Optional<Car> carO = carRepository.findById(id);
        if(carO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        Car updatedCar = carO.get();
        BeanUtils.copyProperties(carDTO, updatedCar);
        return ResponseEntity.status(HttpStatus.OK).body(carRepository.save(updatedCar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable(value = "id") String id) {
        Optional<Car> carO = carRepository.findById(id);
        if(carO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        carRepository.delete(carO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
