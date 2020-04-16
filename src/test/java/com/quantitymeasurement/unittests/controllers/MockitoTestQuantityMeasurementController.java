package com.quantitymeasurement.unittests.controllers;

import com.google.gson.Gson;
import com.quantitymeasurement.controller.QuantityMeasurementController;
import com.quantitymeasurement.dto.UnitResponseDTO;
import com.quantitymeasurement.dto.UnitsConversionDTO;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.enums.UnitType;
import com.quantitymeasurement.exceptions.QuantityMeasurementException;
import com.quantitymeasurement.services.UnitConversionService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(QuantityMeasurementController.class)
public class MockitoTestQuantityMeasurementController {
    Gson gson;
    UnitsConversionDTO unitsConversionDTO;
    String jsonDTO;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        gson = new Gson();
        unitsConversionDTO = new UnitsConversionDTO(35.6, SubUnits.FAHRENHEIT, SubUnits.CELSIUS);
        jsonDTO = gson.toJson(unitsConversionDTO);
    }

    @MockBean
    private UnitConversionService service;

    @Test
    public void givenUnitsDto_whenRequestImproper_thenReturnStatus404() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }

    @Test
    public void testGetUnitType() throws Exception {
        UnitType[] expectedArray = {UnitType.LENGTH, UnitType.WEIGHT, UnitType.VOLUME, UnitType.TEMPERATURE};
        given(service.getUnitTypeList()).willReturn(expectedArray);
        mockMvc.perform(get("/unit/type"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(Arrays.toString(expectedArray)));
    }

    @Test
    public void testGetSubUnitType() throws Exception {
        List<SubUnits> expectedList = new ArrayList<>();
        expectedList.add(SubUnits.FEET);
        expectedList.add(SubUnits.INCH);
        expectedList.add(SubUnits.YARD);
        expectedList.add(SubUnits.CENTIMETER);
        given(service.getSubUnitList("LENGTH")).willReturn(expectedList);
        mockMvc.perform(get("/unit/type/LENGTH"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(String.valueOf(expectedList)));
    }

    @Test
    public void testGetConvertedValue() throws Exception {
        when(service.getConvertedValue(any())).thenReturn(12.0);
        MvcResult mvcResult = mockMvc.perform(post("/unit/converter")
                .content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        UnitResponseDTO unitResponseDTO = gson.fromJson(contentAsString, UnitResponseDTO.class);
        Double value = (Double) unitResponseDTO.getValue();
        Assert.assertEquals(12, value, 0.0);
    }

    @Test
    public void testGetConvertedValueForTemperature() throws Exception {
        UnitsConversionDTO unitsConversionDTO = new UnitsConversionDTO(35.6, SubUnits.FAHRENHEIT, SubUnits.CELSIUS);
        String jsonDTO = gson.toJson(unitsConversionDTO);
        UnitResponseDTO unitResponseDTO = new UnitResponseDTO(2, "Response Successful", 200);
        String JsonUnitsResponseDTO = gson.toJson(unitResponseDTO);
        when(service.getConvertedValue(any())).thenReturn(2.0);
        mockMvc.perform(post("/unit/converter")
                .content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUnitsResponseDTO));
    }

    @Test
    public void testGetConvertedValueForWrongUnits() throws Exception {
        UnitResponseDTO unitResponseDTO = new UnitResponseDTO(12, "Response Successful", 200);
        String JsonUnitsResponseDTO = gson.toJson(unitResponseDTO);
        when(service.getConvertedValue(any())).thenReturn(12.0);
        mockMvc.perform(post("/unit/converter")
                .content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUnitsResponseDTO));
    }


    @Test
    public void givenUnitsDto_whenWrongContentType_thenReturnStatus415() throws Exception {
        when(service.getConvertedValue(unitsConversionDTO)).thenReturn(0.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/converter").content(jsonDTO)
                .contentType(MediaType.APPLICATION_XML)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(415, status);
    }

    @Test
    public void givenUnitsDto_whenWongContent_thenReturnStatus400() throws Exception {
        when(service.getConvertedValue(unitsConversionDTO)).thenReturn(0.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/converter").content(unitsConversionDTO.toString())
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    public void givenUnitsDto_whenWongContent_thenReturnException() throws Exception {
        try {
            unitsConversionDTO = new UnitsConversionDTO(0.0, SubUnits.FEET, SubUnits.LITRE);
            when(service.getConvertedValue(unitsConversionDTO)).thenThrow(new QuantityMeasurementException( "Unit Type Mismatch",QuantityMeasurementException.Type.UNIT_TYPE_MISMATCH));
            mockMvc.perform(post("/unit/converter").content(unitsConversionDTO.toString())
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
        } catch (QuantityMeasurementException m) {
            Assert.assertEquals(QuantityMeasurementException.Type.UNIT_TYPE_MISMATCH, m.type);
        }
    }
}