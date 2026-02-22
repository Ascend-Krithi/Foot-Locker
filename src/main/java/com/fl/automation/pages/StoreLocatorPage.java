package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private By zipInput = By.id("zip-input");
    private By cityInput = By.id("city-input");
    private By searchButton = By.id("search-btn");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void searchByZip(String zip) {
        driver.findElement(zipInput).clear();
        driver.findElement(zipInput).sendKeys(zip);
        driver.findElement(searchButton).click();
    }

    public void searchByCity(String city) {
        driver.findElement(cityInput).clear();
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(searchButton).click();
    }
}
