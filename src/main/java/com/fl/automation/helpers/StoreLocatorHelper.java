package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void openStoreLocator() {
        WebElement selectStore = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Select my store')]")))
;
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectStore);
    }

    public WebElement getLocationTextbox() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Enter address, city or post code']")));
    }

    public void searchLocation(String location) {
        WebElement box = getLocationTextbox();
        box.clear();
        box.sendKeys(location);

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Search for Stores')]")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);
    }
}