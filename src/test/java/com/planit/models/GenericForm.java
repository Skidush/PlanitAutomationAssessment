package com.planit.models;

import java.util.HashMap;

public class GenericForm {
    private final HashMap<String, HashMap<String, Object>> _formModel;

    public GenericForm(HashMap<String, HashMap<String, Object>> formModel) {
        _formModel = formModel;
    }

    public String getValueForField(String fieldName) {
        return returnNullOrKeyValue(fieldName, "value");
    }

    public String getErrorForField(String fieldName) {
        return returnNullOrKeyValue(fieldName, "error");
    }

    public String getElementBySelectorForField(String fieldName) {
        return returnNullOrKeyValue(fieldName, "by");
    }

    public HashMap<String, HashMap<String, Object>> getFormModel() {
        return _formModel;
    }

    private String returnNullOrKeyValue(String fieldName, String innerLevelKeyName) {
        return !_formModel.containsKey(fieldName) ? null : (String) _formModel.get(fieldName).get(innerLevelKeyName);
    }
}
