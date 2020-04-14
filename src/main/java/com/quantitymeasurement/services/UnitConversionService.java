package com.quantitymeasurement.services;

import com.quantitymeasurement.enums.SubUnits;
import com.quantitymeasurement.enums.UnitType;
import com.quantitymeasurement.exceptions.QuantityMeasurementException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitConversionService {

    public UnitType[] getUnitTypeList() {
        return UnitType.values();
    }

    public List<SubUnits> getSubUnitList(UnitType unitType) {
        List<SubUnits> subUnitsList= Arrays.stream(SubUnits.values())
                .filter(subUnits -> subUnits.unitType.equals(unitType))
                .collect(Collectors.toList());
        if (subUnitsList.size() > 0) {
            return subUnitsList;
        }
        throw new QuantityMeasurementException("This type value does not exist", QuantityMeasurementException.Type.UNIT_NOT_AVAILABLE);
    }
}
