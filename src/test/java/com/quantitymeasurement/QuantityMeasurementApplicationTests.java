package com.quantitymeasurement;

import com.google.gson.Gson;
import com.quantitymeasurement.dto.UnitsConversionDTO;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.enums.UnitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = QuantityMeasurementApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuantityMeasurementApplicationTests {
    Gson gson;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        gson = new Gson();
    }

    @Test
    public void quantityMeasurementGetUnitType_ShouldReturnJsonArrayOfUnitsType() {
        UnitType[] expectedArray = {UnitType.LENGTH, UnitType.WEIGHT, UnitType.VOLUME, UnitType.TEMPERATURE};
        String numbersJson = gson.toJson(expectedArray);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/unit/type",
                String.class)).contains(numbersJson);
    }

    @Test
    public void givenUnitTypeLength_ShouldReturnSubUnitsTypeListOfLength() {
        List<SubUnits> expectedList = new ArrayList<>();
        expectedList.add(SubUnits.FEET);
        expectedList.add(SubUnits.INCH);
        expectedList.add(SubUnits.YARD);
        expectedList.add(SubUnits.CENTIMETER);
        String numbersJson = gson.toJson(expectedList);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "unit/type/LENGTH",
                String.class)).contains(numbersJson);
    }

    @Test
    public void givenUnitTypeVolume_ShouldReturnSubUnitsTypeListOfVolume() {
        List<SubUnits> expectedList = new ArrayList<>();
        expectedList.add(SubUnits.LITRE);
        expectedList.add(SubUnits.GALLON);
        expectedList.add(SubUnits.MILLILITER);
        String numbersJson = gson.toJson(expectedList);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "unit/type/VOLUME",
                String.class)).contains(numbersJson);
    }

    @Test
    public void givenUnitTypeWeight_ShouldReturnSubUnitsTypeListOfWeight() {
        List<SubUnits> expectedList = new ArrayList<>();
        expectedList.add(SubUnits.KILOGRAM);
        expectedList.add(SubUnits.GRAMS);
        expectedList.add(SubUnits.TON);
        String numbersJson = gson.toJson(expectedList);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "unit/type/WEIGHT",
                String.class)).contains(numbersJson);
    }

    @Test
    public void givenUnitTypeTemperature_ShouldReturnSubUnitsTypeListOfTemperature() {
        List<SubUnits> expectedList = new ArrayList<>();
        expectedList.add(SubUnits.FAHRENHEIT);
        expectedList.add(SubUnits.CELSIUS);
        String numbersJson = gson.toJson(expectedList);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "unit/type/TEMPERATURE",
                String.class)).contains(numbersJson);
    }
}
