package com.planit.enums;

import java.util.HashMap;

public class FormErrors {
    public enum Contact {
        FORENAME("Forename is required"),
        EMAIL("Email is required"),
        MESSAGE("Message is required");

        private final String _fieldName;

        Contact(String fieldName) {
            _fieldName = fieldName;
        }

        public String toString() {
            return _fieldName;
        }
    }
}
