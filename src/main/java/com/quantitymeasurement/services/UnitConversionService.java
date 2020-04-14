package com.quantitymeasurement.services;

import com.quantitymeasurement.enums.UnitType;
import org.springframework.stereotype.Service;

@Service
public class UnitConversionService {

    public UnitType[] getUnitTypeList() {
        return UnitType.values();
    }
}
