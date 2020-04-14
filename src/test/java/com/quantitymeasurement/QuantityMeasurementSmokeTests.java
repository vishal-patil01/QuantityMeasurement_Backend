package com.quantitymeasurement;

import com.quantitymeasurement.controller.QuantityMeasurementController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuantityMeasurementSmokeTests {

    @Autowired
    private QuantityMeasurementController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
