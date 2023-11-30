package com.rentalCars.rentalCars.domain.cars;

import com.rentalCars.rentalCars.domain.cars.dto.CarDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "TB_CARS")
@Entity(name = "TB_CARS")
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
    private String engine;
    private Number door;
    private Boolean gearbox;
    private String fuel;
    private String dimensions;
    private Number trunk;
    private Number avgConsumptionCity;
    private Number avgConsumptionRoad;
    private Number power;
    private Boolean isRented;
    private BigDecimal price;

    public Car(CarDTO carDTO) {
        this.model = carDTO.model();
        this.engine = carDTO.engine();
        this.door = carDTO.door();
        this.gearbox = carDTO.gearbox();
        this.fuel = carDTO.fuel();
        this.dimensions = carDTO.dimensions();
        this.trunk = carDTO.trunk();
        this.avgConsumptionCity = carDTO.avgConsumptionCity();
        this.avgConsumptionRoad = carDTO.avgConsumptionRoad();
        this.power = carDTO.power();
        this.isRented = carDTO.isRented();
        this.price = carDTO.price();
    }
}
