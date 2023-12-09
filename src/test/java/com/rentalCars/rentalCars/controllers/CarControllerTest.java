package com.rentalCars.rentalCars.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentalCars.rentalCars.domain.cars.Car;
import com.rentalCars.rentalCars.domain.cars.dto.CarDTO;
import com.rentalCars.rentalCars.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureJsonTesters
class CarControllerTest {

    @Autowired
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarController carController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<CarDTO> carJSON;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should return a list of all active cars.")
    @WithMockUser
    void getAllCars() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    List<String> cars = Arrays.asList(result.getResponse().getContentAsString());

                    assertThat(cars).isNotEmpty();
                });
    }

    @Test
    @DisplayName("Should return status 400 when body is empty.")
    @WithMockUser
    void addCarErrorScenario() throws Exception {
        var response = mockMvc.perform(post("/cars"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Should create a car.")
    @WithMockUser
    void addCar() throws Exception {
        var response = mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(carJSON.write(
                        new CarDTO("VOLKSWAGEN POLO TRACK 1.0",
                        "Branco",
                        "1.0 ",
                        "4",
                        "Hidraulica",
                        "Flex (alcool/gasolina)",
                        "407 cm x 175 cm (espelho a espelho) x 147 cm",
                        "300",
                        " 9,1 / 12,8 Km/l",
                        " 9,1 / 12,8 Km/l",
                        "80",
                        false,
                        "1",
                        200.00,
                        "4",
                        "Freio ABS")).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var expectedJSON = carJSON.write(
                new CarDTO(
                        "VOLKSWAGEN POLO TRACK 1.0",
                        "Branco",
                        "1.0 ",
                        "4",
                        "Hidraulica",
                        "Flex (alcool/gasolina)",
                        "407 cm x 175 cm (espelho a espelho) x 147 cm",
                        "300",
                        " 9,1 / 12,8 Km/l",
                        " 9,1 / 12,8 Km/l",
                        "80",
                        false,
                        "1",
                        200.00,
                        "4",
                        "Freio ABS"
                )
        ).getJson();
        JSONAssert.assertEquals(expectedJSON, response.getContentAsString(), false);
    }

    @Test
    @DisplayName("Should return a single car by id.")
    void getOneCar() throws Exception {
        MvcResult response = mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJSON.write(
                                new CarDTO("VOLKSWAGEN POLO TRACK 1.0",
                                        "Branco",
                                        "1.0 ",
                                        "4",
                                        "Hidraulica",
                                        "Flex (alcool/gasolina)",
                                        "407 cm x 175 cm (espelho a espelho) x 147 cm",
                                        "300",
                                        " 9,1 / 12,8 Km/l",
                                        " 9,1 / 12,8 Km/l",
                                        "80",
                                        false,
                                        "1",
                                        200.00,
                                        "4",
                                        "Freio ABS")).getJson()))
                .andReturn();
        String content = response.getResponse().getContentAsString();
        Car createdCar = new ObjectMapper().readValue(content, Car.class);
        String carId = createdCar.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/{id}", carId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should update an active car by id.")
    void updateCar() throws Exception{
        String[] colorsArray = {"Preto", "Branco"};
        List<String> colors = new ArrayList<>(Arrays.asList(colorsArray));
        Collections.shuffle(colors);
        String[] randomColor = colors.toArray(new String[0]);
        var response = mockMvc.perform(put("/cars/6716a9b5-4685-4114-8719-41004ee0082c")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJSON.write(
                                new CarDTO("VOLKSWAGEN POLO TRACK 1.0",
                                        Arrays.toString(randomColor),
                                        "1.0 ",
                                        "4",
                                        "Hidraulica",
                                        "Flex (alcool/gasolina)",
                                        "407 cm x 175 cm (espelho a espelho) x 147 cm",
                                        "300",
                                        " 9,1 / 12,8 Km/l",
                                        " 9,1 / 12,8 Km/l",
                                        "80",
                                        false,
                                        "1",
                                        200.00,
                                        "4",
                                        "Freio ABS")).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJSON = carJSON.write(
                new CarDTO(
                        "VOLKSWAGEN POLO TRACK 1.0",
                        Arrays.toString(randomColor),
                        "1.0 ",
                        "4",
                        "Hidraulica",
                        "Flex (alcool/gasolina)",
                        "407 cm x 175 cm (espelho a espelho) x 147 cm",
                        "300",
                        " 9,1 / 12,8 Km/l",
                        " 9,1 / 12,8 Km/l",
                        "80",
                        false,
                        "1",
                        200.00,
                        "4",
                        "Freio ABS"
                )
        ).getJson();
        JSONAssert.assertEquals(expectedJSON, response.getContentAsString(), false);
    }

    @Test
    @DisplayName("Should delete a car by id.")
    @WithMockUser
    void deleteCar() throws Exception{
                MvcResult response = mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJSON.write(
                                new CarDTO("VOLKSWAGEN POLO TRACK 1.0",
                                        "Branco",
                                        "1.0 ",
                                        "4",
                                        "Hidraulica",
                                        "Flex (alcool/gasolina)",
                                        "407 cm x 175 cm (espelho a espelho) x 147 cm",
                                        "300",
                                        " 9,1 / 12,8 Km/l",
                                        " 9,1 / 12,8 Km/l",
                                        "80",
                                        false,
                                        "1",
                                        200.00,
                                        "4",
                                        "Freio ABS")).getJson()))
                        .andReturn();
                String content = response.getResponse().getContentAsString();
                Car createdCar = new ObjectMapper().readValue(content, Car.class);
                String carIdToDelete = createdCar.getId();

                mockMvc.perform(MockMvcRequestBuilders.delete("/cars/{id}", carIdToDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

                boolean carExists = carRepository.existsById(carIdToDelete);
                assertThat(carExists).isFalse();
    }
}