package com.planit.components;

import com.planit.enums.HeaderButtons.HEADER_LINKS;
import com.planit.helpers.LazyElement;
import com.planit.pageobjects.CartPage;
import com.planit.pageobjects.ContactPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends JupiterToysComponent {
    public HeaderComponent(WebDriver webDriver, String parentSelector) {
        super(webDriver, parentSelector);
    }

    public LazyElement getHeaderLink(HEADER_LINKS headerLinkName) {
        return new LazyElement(new By.ByCssSelector(parentSelector + " a[href=\"#/"+ headerLinkName.toString() + "\"]"));
    }

    public <T> T navigateTo(HEADER_LINKS headerLinkName) throws Exception {
        getHeaderLink(headerLinkName).click();

        T pageOpened = null;
        switch (headerLinkName) {
            case CONTACT:
                pageOpened = (T) new ContactPage(webDriver);
                break;
            case CART:
                pageOpened = (T) new CartPage(webDriver);
                break;
            default:
                System.out.println("oh noe: " + headerLinkName);
        }
        return pageOpened;
    }

}
