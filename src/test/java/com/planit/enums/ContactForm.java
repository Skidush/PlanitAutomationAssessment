package com.planit.enums;

public class ContactForm {
    public enum FieldWithError {
        FORENAME("Forename is required"),
        EMAIL("Email is required"),
        MESSAGE("Message is required");

        private final String _fieldName;

        FieldWithError(String fieldName) {
            _fieldName = fieldName;
        }

        public String toString() {
            return _fieldName;
        }
    }

    public enum Fields {
        FORENAME("text"),
        EMAIL("email"),
        MESSAGE("text");

        private final String _fieldName;

        Fields(String fieldName) {
            _fieldName = fieldName;
        }

        public String toString() {
            return _fieldName;
        }
    }
}
