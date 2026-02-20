package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSelectMyStoreLink() {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                return WaitUtils.waitForDisplayed(driver, locator, 15);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store link/button not found");
    }

    public void clickSelectMyStore() {
        getSelectMyStoreLink().click();
    }

    public WebElement getSearchInput() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                return WaitUtils.waitForDisplayed(driver, locator, 15);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found");
    }

    public void enterLocation(String location) {
        getSearchInput().clear();
        getSearchInput().sendKeys(location);
    }

    public WebElement getSearchButton() {
        // Assume search button is next to input
        WebElement input = getSearchInput();
        WebElement parent = input.findElement(By.xpath(".."));
        return parent.findElement(By.xpath(".//button[contains(.,'Search')]"));
    }

    public void clickSearchButton() {
        getSearchButton().click();
    }
}