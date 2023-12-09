package com.rentalCars.rentalCars.domain.cars;

import com.rentalCars.rentalCars.domain.cars.dto.CarDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "cars")
@Entity(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String model;
    private String color;
    private String engine;
    private String doors;
    private String gearbox;
    private String fuel;
    private String dimensions;
    private String trunk;
    private String avgConsumptionCity;
    private String avgConsumptionRoad;
    private String power;
    private Boolean isRented;
    private String rentedBy;
    private Double price;
    private String airbag;
    private String brake;

    public Car(CarDTO carDTO) {
        this.model = carDTO.model();
        this.color = carDTO.color();
        this.engine = carDTO.engine();
        this.doors = carDTO.doors();
        this.gearbox = carDTO.gearbox();
        this.fuel = carDTO.fuel();
        this.dimensions = carDTO.dimensions();
        this.trunk = carDTO.trunk();
        this.avgConsumptionCity = carDTO.avgConsumptionCity();
        this.avgConsumptionRoad = carDTO.avgConsumptionRoad();
        this.power = carDTO.power();
        this.isRented = carDTO.isRented();
        this.rentedBy = carDTO.rentedBy();
        this.price = carDTO.price();
        this.airbag = carDTO.airbag();
        this.brake = carDTO.brake();
    }
}
