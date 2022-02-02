package com.netcracker.hwapp.enums;

public enum Status {
    CREATED("Создано"),
    SENT("Отправлено"),
    CHECKED("Проверено");

    private final String displayValue;

    Status(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
