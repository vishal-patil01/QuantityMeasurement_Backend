package com.quantitymeasurement.unittests;

import com.quantitymeasurement.controller.QuantityMeasurementController;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.enums.UnitType;
import com.quantitymeasurement.services.UnitConversionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuantityMeasurementController.class)
public class MockitoTestQuantityMeasurementController {
    @Autowired
    private QuantityMeasurementController controller;
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitConversionService service;

    @Test
    public void quantityMeasurementGetUnitType_ShouldReturnJsonArrayOfUnitsType() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        UnitType[] expectedArray = {UnitType.LENGTH, UnitType.WEIGHT, UnitType.VOLUME, UnitType.TEMPERATURE};
        given(service.getUnitTypeList()).willReturn(expectedArray);
        mvc.perform(get("/unittype"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(Arrays.toString(expectedArray)));
    }

    @Test
    public void givenUnitTypeLength_ShouldReturnSubUnitsTypeListOfLength() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        List<SubUnits> expectedList = new ArrayList<>();
        expectedList.add(SubUnits.FEET);
        expectedList.add(SubUnits.INCH);
        expectedList.add(SubUnits.YARD);
        expectedList.add(SubUnits.CENTIMETER);
        given(service.getSubUnitList(UnitType.LENGTH)).willReturn(expectedList);
        mvc.perform(get("/unittype/LENGTH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(String.valueOf(expectedList)));
    }

    @Test
    public void givenUnitTypeVolume_ShouldReturnSubUnitsTypeListOfVolume() throws Exception {
        List<SubUnits> expectedList = new ArrayList<>();
        expectedList.add(SubUnits.LITRE);
        expectedList.add(SubUnits.GALLON);
        expectedList.add(SubUnits.YARD);
        expectedList.add(SubUnits.CENTIMETER);
        given(service.getSubUnitList(UnitType.LENGTH)).willReturn(expectedList);
        mvc.perform(get("/unittype/LENGTH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(String.valueOf(expectedList)));
    }
}