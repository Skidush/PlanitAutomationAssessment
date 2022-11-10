package com.planit.builders;

import com.planit.models.GenericForm;

import java.util.HashMap;

public class FormBuilder {
    private final HashMap<String, HashMap<String, Object>> _fields = new HashMap<>();

    public FormBuilder() {}

    private void addField(String fieldName) {
        _fields.put(fieldName, new HashMap<>());
    }

    public void mapFieldToProperty(String fieldName, String propertyName, String propertyValue) {
        if (!_fields.containsKey(fieldName)) {
            addField(fieldName);
        }
        HashMap<String, Object> fieldMap = _fields.get(fieldName);
        fieldMap.put(propertyName, propertyValue);

        _fields.put(fieldName, fieldMap);
    }
    public GenericForm build() {
        return new GenericForm(_fields);
    }
}
