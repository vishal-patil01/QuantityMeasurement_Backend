package com.quantitymeasurement.unittests;

import com.quantitymeasurement.controller.QuantityMeasurementController;
import com.quantitymeasurement.enums.UnitType;
import com.quantitymeasurement.services.UnitConversionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuantityMeasurementController.class)
public class MockitoTestQuantityMeasurementController {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitConversionService service;

    @Test
    public void quantityMeasurementGetUnitType_ShouldReturnJsonArrayOfUnitsType() throws Exception {
        UnitType[] expectedArray = {UnitType.LENGTH, UnitType.WEIGHT, UnitType.VOLUME, UnitType.TEMPERATURE};
        given(service.getUnitTypeList()).willReturn(expectedArray);
        mvc.perform(get("/unittype"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(Arrays.toString(expectedArray)));
    }
}