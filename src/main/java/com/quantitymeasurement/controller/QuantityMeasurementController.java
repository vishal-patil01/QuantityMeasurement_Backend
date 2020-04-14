package com.quantitymeasurement.controller;


import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.enums.UnitType;
import com.quantitymeasurement.services.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class QuantityMeasurementController {

    @Autowired
    UnitConversionService unitConversionService;

    @GetMapping("/unitType")
    public ResponseEntity<UnitType[]> getUnitType() {
        return new ResponseEntity<>(unitConversionService.getUnitTypeList(), HttpStatus.OK);
    }
    @GetMapping("/unittype/{unit}")
    public ResponseEntity<List<SubUnits>> getAllSubUnits(@PathVariable UnitType unit) {
        return new ResponseEntity<>(unitConversionService.getSubUnitList(unit), HttpStatus.OK);
    }
}
