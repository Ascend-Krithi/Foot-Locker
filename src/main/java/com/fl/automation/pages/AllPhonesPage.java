package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AllPhonesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By deviceList = By.xpath("//div[contains(@class,'device-card') or contains(@class,'product-card')]");
    private By deviceNameLocator = By.xpath("//h3 | //h4 | //span[contains(@class,'device-name')]");

    public AllPhonesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isAllPhonesPageLoaded() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(deviceList));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDeviceListDisplayed() {
        try {
            List<WebElement> devices = driver.findElements(deviceList);
            return devices.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAtLeastOneDeviceListed() {
        try {
            List<WebElement> devices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(deviceList));
            return devices.size() >= 1;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDevice(String deviceName) {
        try {
            WebElement device = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h3[contains(text(),'" + deviceName + "')] | //h4[contains(text(),'" + deviceName + "')] | //span[contains(text(),'" + deviceName + "')]"))
            );
            try {
                device.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", device);
            }
        } catch (Exception e) {
            System.out.println("Device not found: " + deviceName);
        }
    }

    public boolean isDeviceVisible(String deviceName) {
        try {
            WebElement device = driver.findElement(
                By.xpath("//h3[contains(text(),'" + deviceName + "')] | //h4[contains(text(),'" + deviceName + "')] | //span[contains(text(),'" + deviceName + "')]"))
            );
            return device.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}