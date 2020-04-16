package com.quantitymeasurement.exceptions;

public class QuantityMeasurementException extends RuntimeException {
    public enum Type {UNIT_TYPE_MISMATCH, UNIT_NOT_AVAILABLE}

    public Type type;
    public String message;

    public QuantityMeasurementException(String message, Type type) {
        this.message = message;
        this.type = type;
    }
}
