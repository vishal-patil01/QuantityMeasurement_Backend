package com.quantitymeasurement;

import com.google.gson.Gson;
import com.quantitymeasurement.enums.UnitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = QuantityMeasurementApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuantityMeasurementApplicationTests {
    Gson gson;

    @LocalServerPort
    private int port = 8080;

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
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/unittype",
                String.class)).contains(numbersJson);
    }

}
