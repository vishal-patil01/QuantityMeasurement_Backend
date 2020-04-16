package com.quantitymeasurement.controller;


import com.quantitymeasurement.dto.UnitResponseDTO;
import com.quantitymeasurement.dto.UnitsConversionDTO;
import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.enums.UnitType;
import com.quantitymeasurement.services.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController()
public class QuantityMeasurementController {

    @Autowired
    UnitConversionService unitConversionService;

    @GetMapping("/unit/type")
    public ResponseEntity<UnitType[]> getUnitType() {
        return new ResponseEntity<>(unitConversionService.getUnitTypeList(), HttpStatus.OK);
    }
    @GetMapping("/unit/type/{unit}")
    public ResponseEntity<List<SubUnits>> getAllSubUnits(@PathVariable String unit) {
        return new ResponseEntity<>(unitConversionService.getSubUnitList(unit), HttpStatus.OK);
    }

    @PostMapping("unit/converter")
    public ResponseEntity<UnitResponseDTO> getUnitData(@RequestBody UnitsConversionDTO unitsConversionDTO) {
        Double convertedValue = unitConversionService.getConvertedValue(unitsConversionDTO);
        UnitResponseDTO unitResponseDTO = new UnitResponseDTO(convertedValue, "Response Successful",200);
        return new ResponseEntity<>(unitResponseDTO, HttpStatus.OK);
    }
}
