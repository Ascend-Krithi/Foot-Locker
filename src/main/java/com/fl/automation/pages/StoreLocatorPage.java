package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;

    private By zipCodeInput = By.id("zip-code-input");
    private By searchButton = By.id("search-button");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void enterZipCode(String zip) {
        WebElement input = driver.findElement(zipCodeInput);
        input.clear();
        input.sendKeys(zip);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }
}
