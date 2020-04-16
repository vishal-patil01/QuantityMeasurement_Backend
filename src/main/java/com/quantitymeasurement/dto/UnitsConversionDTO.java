package com.quantitymeasurement.dto;

import com.quantitymeasurement.enums.SubUnits;

public class UnitsConversionDTO {
    private Double value;
    private SubUnits firstUnitType;
    private SubUnits secondUnitType;

    public UnitsConversionDTO(double value, SubUnits firstUnitType, SubUnits secondUnitType) {
        this.value = value;
        this.firstUnitType = firstUnitType;
        this.secondUnitType = secondUnitType;
    }

    public Double getValue() {
        return value;
    }

    public SubUnits getFirstUnitType() {
        return firstUnitType;
    }

    public SubUnits getSecondUnitType() {
        return secondUnitType;
    }
}
