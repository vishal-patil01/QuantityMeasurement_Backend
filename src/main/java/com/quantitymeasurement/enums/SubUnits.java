package com.quantitymeasurement.enums;

public enum SubUnits {
    FEET(UnitType.LENGTH),
    INCH(UnitType.LENGTH),
    YARD(UnitType.LENGTH),
    CENTIMETER(UnitType.LENGTH),
    LITRE(UnitType.VOLUME),
    GALLON(UnitType.VOLUME),
    MILLILITER(UnitType.VOLUME),
    KILOGRAM(UnitType.WEIGHT),
    GRAMS(UnitType.WEIGHT),
    TON(UnitType.WEIGHT),
    FAHRENHEIT(UnitType.TEMPERATURE),
    CELSIUS(UnitType.TEMPERATURE);

    public UnitType unitType;

    SubUnits(UnitType unitType) {
        this.unitType = unitType;
    }
}