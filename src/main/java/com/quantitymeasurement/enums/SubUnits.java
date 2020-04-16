package com.quantitymeasurement.enums;

public enum SubUnits {

    FEET(UnitType.LENGTH, 12),
    INCH(UnitType.LENGTH, 1),
    YARD(UnitType.LENGTH, 36),
    CENTIMETER(UnitType.LENGTH, (1 / 2.5)),
    LITRE(UnitType.VOLUME, 1),
    GALLON(UnitType.VOLUME, 3.78),
    MILLILITER(UnitType.VOLUME, 0.001),
    KILOGRAM(UnitType.WEIGHT, 1),
    GRAMS(UnitType.WEIGHT, 0.001),
    TON(UnitType.WEIGHT, 1000),
    FAHRENHEIT(UnitType.TEMPERATURE, 0.5555555555555556),
    CELSIUS(UnitType.TEMPERATURE, 1.8);

    public double unitValue;
    public UnitType unitType;

    SubUnits(UnitType unitType, double value) {
        this.unitType = unitType;
        this.unitValue = value;
    }
}