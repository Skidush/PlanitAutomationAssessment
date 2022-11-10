package com.planit.enums;

public enum InputType {
    TEXT("text"),
    NUMBER("number"),
    EMAIL("email");

    private final String _inputType;

    InputType(String inputType) {
        _inputType = inputType;
    }

    public String toString() {
        return _inputType;
    }
}
