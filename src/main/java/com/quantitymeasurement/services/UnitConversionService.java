package com.quantitymeasurement.services;

import com.quantitymeasurement.dto.UnitsConversionDTO;
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

    public List<SubUnits> getSubUnitList(String unitType) {
        List<SubUnits> subUnitsList = Arrays.stream(SubUnits.values()).filter(subUnits -> subUnits.unitType.toString().equals(unitType))
                .collect(Collectors.toList());
        System.out.println(subUnitsList);
        if (subUnitsList.size() > 0) {
            return subUnitsList;
        }
        throw new QuantityMeasurementException("This Type Of Unit Not Exist", QuantityMeasurementException.Type.UNIT_NOT_AVAILABLE);
    }

    public Double getConvertedValue(UnitsConversionDTO unitsConversionDTO) {
        if (unitsConversionDTO.getFirstUnitType().unitType.equals(unitsConversionDTO.getSecondUnitType().unitType)) {
            return conversionForNormalUnits(unitsConversionDTO);
        }
        throw new QuantityMeasurementException("Unit Type Mismatch : '"+unitsConversionDTO.getFirstUnitType()+"' Can Not Be Converted To '"+unitsConversionDTO.getSecondUnitType()+"'", QuantityMeasurementException.Type.UNIT_TYPE_MISMATCH);
    }

    public Double conversionForNormalUnits(UnitsConversionDTO unitsConversionDTO) {
        if (unitsConversionDTO.getFirstUnitType().unitType.equals(UnitType.TEMPERATURE)) {
            return conversionForTemperatureUnits(unitsConversionDTO);
        }
        return (unitsConversionDTO.getValue() * unitsConversionDTO.getFirstUnitType().unitValue) / unitsConversionDTO.getSecondUnitType().unitValue;
    }

    public Double conversionForTemperatureUnits(UnitsConversionDTO unitsConversionDTO) {
        if (unitsConversionDTO.getFirstUnitType() == SubUnits.CELSIUS && unitsConversionDTO.getSecondUnitType() == SubUnits.FAHRENHEIT) {
            return (unitsConversionDTO.getValue() * unitsConversionDTO.getFirstUnitType().unitValue) + 32;
        } else if (unitsConversionDTO.getFirstUnitType() == SubUnits.FAHRENHEIT && unitsConversionDTO.getSecondUnitType() == SubUnits.CELSIUS) {
            return (unitsConversionDTO.getValue() - 32) * unitsConversionDTO.getFirstUnitType().unitValue;
        }
        return (unitsConversionDTO.getValue() * unitsConversionDTO.getFirstUnitType().unitValue) / unitsConversionDTO.getSecondUnitType().unitValue;
    }
}
