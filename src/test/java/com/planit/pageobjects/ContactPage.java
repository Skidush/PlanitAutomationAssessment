package com.planit.pageobjects;

import com.planit.builders.FormBuilder;
import com.planit.enums.ContactForm;
import com.planit.helpers.LazyElement;
import com.planit.models.GenericForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class ContactPage extends JupiterToysPage<ContactPage> {
    private final By _errorMessagesSelector = new By.ByCssSelector("[required=\"required\"] ~ span.help-inline");
    private final String _parentSelectorWhenValid;

    public GenericForm contactFormDetails = null;
    public boolean isPageValid = false;

    public ContactPage(WebDriver webDriver) {
        super(webDriver, "Jupiter Toys",
                "div.ng-scope[ui-if=\"!contactValidSubmit\"]",
                "contact");

        _parentSelectorWhenValid = parentSelector.replace("!", "");
    }

    public LazyElement getSubmitButton() {
        return new LazyElement(new By.ByCssSelector(parentSelector + " a.btn-contact.btn-primary"));
    }

    public LazyElement getFormField(String formFieldId) {
        return new LazyElement(new By.ById(formFieldId));
    }

    public LazyElement getErrorAlert() {
        return new LazyElement(new By.ByCssSelector(parentSelector + " div.alert.alert-error"));
    }

    public LazyElement getSuccessAlert() {
        return new LazyElement(new By.ByCssSelector(_parentSelectorWhenValid + " div.alert.alert-success"));
    }

    public LazyElement getSendingFeedbackModal() {
        return new LazyElement(new By.ByCssSelector("div.popup.modal"));
    }

    public LazyElement getBackButton() throws Exception {
        if (!isPageValid) {
            throw new Exception("The page is not valid, thus no back button element exists!");
        }

        return new LazyElement(new By.ByCssSelector(_parentSelectorWhenValid + " a[ng-click=\"goBack()\"]"));
    }

    public HashMap<String, String> getFormFieldsError() throws Exception {
        HashMap<String, String> fieldToErrorMap = new HashMap<>();

        LazyElement errorMessageElement = new LazyElement(_errorMessagesSelector);
        errorMessageElement.waitToLoad(true);

        List<WebElement> errorMessages = webDriver.findElements(_errorMessagesSelector);
        errorMessages.forEach(element -> {
            fieldToErrorMap.put(element.getAttribute("id").replace("-err", ""), element.getText());
        });

        return fieldToErrorMap;
    }

    public boolean isFormValid() throws Exception {
        LazyElement errorMessageElement = new LazyElement(_errorMessagesSelector);
        return errorMessageElement.waitForAbsence() && getErrorAlert().waitForAbsence();
    }

    public String getSuccessAlertMessage() throws Exception {
        if (!isPageValid) {
            throw new Exception("The form has not yet been successfully completed, thus no success messages will appear!");
        }
        return this.getSuccessAlert().getText();
    }

    public GenericForm buildContactForm() {
        FormBuilder fb = new FormBuilder();

        for (ContactForm.Fields field : ContactForm.Fields.values()) {
            String fieldName = field.name().toLowerCase();

            if (field != ContactForm.Fields.EMAIL) {
                fb.mapFieldToProperty(fieldName, "value", RandomStringUtils.randomAlphanumeric(8));
            } else {
                fb.mapFieldToProperty(fieldName, "value", "randomEmail@testdata.com");
            }
        }

        contactFormDetails = fb.build();
        return contactFormDetails;
    }

    public ContactPage waitForSendingFeedbackModalToDisappear() throws Exception {
        try {
            this.getSendingFeedbackModal().waitForAbsence();
        } catch (Exception e) {
            throw e;
        }

        return this;
    }

    public ContactPage goBack() throws Exception {
        this.getBackButton().click();
        this.isPageValid = false;

        return this;
    }

    public ContactPage fillForm() throws RuntimeException {
        contactFormDetails.getFormModel().forEach((fieldName, fieldProperty) -> {
            try {
                getFormField(fieldName).sendKeys(fieldProperty.get("value").toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return this;
    }

    public ContactPage submitForm() throws Exception {
        getSubmitButton().click();
        return this;
    }

    public ContactPage buildFillAndSubmitForm() throws Exception {
        this.buildContactForm();
        this.fillForm();
        this.submitForm();
        this.isPageValid = true;

        return this;
    }
}
