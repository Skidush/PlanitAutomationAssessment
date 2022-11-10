package com.planit.uitests;

import com.planit.components.CartItemComponent;
import com.planit.enums.ContactForm;
import com.planit.enums.HeaderButtons;
import com.planit.helpers.CSVDataLoader;
import com.planit.hooks.BaseTest;

import com.planit.pageobjects.CartPage;
import com.planit.pageobjects.ContactPage;
import com.planit.pageobjects.HomePage;
import com.planit.pageobjects.ShopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class WebTests extends BaseTest {

    @Test
    public void TestCase1() throws Exception {
        // From the home page go to contact page
        // Click submit button
        HomePage homePage = new HomePage(driver);

        ContactPage contactPage = homePage.header
                .<ContactPage>navigateTo(HeaderButtons.HEADER_LINKS.CONTACT)
                .submitForm();

        // Verify error messages
        HashMap<String, String> fieldErrors = contactPage.getFormFieldsError();
        Assert.assertTrue(fieldErrors.size() > 0, "The fields did not display error messages!");

        fieldErrors.forEach((fieldName, errorMessage) ->
        {
            try {
                String contactFieldError = ContactForm.FieldWithError.valueOf(fieldName.toUpperCase()).toString();
                if (contactFieldError == null) {
                    Assert.fail("The field '" + fieldName + "' did not have an error!");
                } else {
                    Assert.assertEquals(
                            contactFieldError,
                            errorMessage,
                            "The error messages did not match!");
                }
            } catch (NoSuchElementException e) {
                Assert.fail("The field '" + fieldName + "' threw an error when it shouldn't have.");
            }
        });

        try {
            contactPage.getErrorAlert().waitToLoad(false);
        } catch (Exception e) {
            Assert.fail("The alert error message is not displayed!");
        }

        // Populate mandatory fields
        contactPage
                .buildFillAndSubmitForm()
                .goBack();

        // Validate errors are gone
        Assert.assertTrue(contactPage.isFormValid(), "There are still error messages in the form!");
    }

    @Test(invocationCount = 5)
    public void TestCase2() throws Exception {
        // From the home page go to contact page
        HomePage homePage = new HomePage(driver);

        // Populate mandatory fields
        // Click submit button
        ContactPage contactPage = homePage.header
                .<ContactPage>navigateTo(HeaderButtons.HEADER_LINKS.CONTACT)
                .buildFillAndSubmitForm();

        // Validate successful submission message
        String successMessage = contactPage.getSuccessAlertMessage();
        String forenameValue = contactPage.contactFormDetails.getValueForField(ContactForm.Fields.FORENAME.name().toLowerCase());
        Assert.assertEquals(successMessage, "Thanks " + forenameValue + ", we appreciate your feedback.");
    }

    @Test
    public void TestCase3() throws Exception {
        HashMap<String, Integer> shopItemsToBuy = new HashMap<String, Integer>() {{
            put("Stuffed Frog", 2);
            put("Fluffy Bunny", 5);
            put("Valentine Bear", 3);
        }};
        /*String[] shopItemsToBuy = {"Stuffed Frog", "Fluffy Bunny", "Valentine Bear"};*/

        // Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
        ShopPage shopPage = new ShopPage(driver).navigate();
        shopItemsToBuy.forEach((item, quantity) -> {
            shopPage.getShopItem(item).buy(quantity);
        });

        // Go to the cart page
        CartPage cartPage = shopPage.header.<CartPage>navigateTo(HeaderButtons.HEADER_LINKS.CART);

        // Verify the subtotal for each product is correct
        // Verify the price for each product
        HashMap<String, Double> shopItems = CSVDataLoader.shopItems;
        shopItemsToBuy.forEach((item, quantity) -> {
            CartItemComponent cartItem = cartPage.getCartItem(item);
            try {
                Assert.assertEquals(
                        cartItem.getSubtotal(),
                        cartItem.getPrice() * cartItem.getQuantity(),
                        "The subtotal is incorrect!");

                Assert.assertEquals(
                        cartItem.getPrice(),
                        (double) shopItems.get(item),
                        "The price for " + item + " is incorrect!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Verify that total = sum(sub totals)
        double calculatedTotal = 0;
        for (Map.Entry<String, Integer> item: shopItemsToBuy.entrySet()) {
            calculatedTotal += shopItems.get(item.getKey()) * item.getValue();
        }
        Assert.assertEquals(cartPage.getTotal(), calculatedTotal, "The total is not the sum of sub totals!");
    }
}
