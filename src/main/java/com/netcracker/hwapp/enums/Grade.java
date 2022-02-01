package com.netcracker.hwapp.enums;

public enum Grade {
    NONE("Не оценено"),
    EXCELLENT("Отлично"),
    GOOD("Хорошо"),
    FAIR("Удовлетворительно"),
    POOR("Неудовлетворительно");


    private final String displayValue;

    private Grade(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
