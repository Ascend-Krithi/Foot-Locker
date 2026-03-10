package com.fl.automation.helpers;

import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {
    private final WebDriver driver;
    private final HomePage homePage;
    private final WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void openHomePage() {
        driver.get("https://www.footlocker.com");
    }

    public boolean isFindStorePopupVisible() {
        try {
            return homePage.waitForFindStoreHeader(wait);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean clickSelectMyStore() {
        try {
            return homePage.clickSelectMyStore(wait);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean enterLocationAndSearch(String location) {
        try {
            return homePage.enterLocationAndSearch(wait, location);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean verifyStoreAddressInResults(String address) {
        try {
            return homePage.isStoreAddressPresent(wait, address);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean setMyStoreForAddress(String address) {
        try {
            return homePage.setMyStoreForAddress(wait, address);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isConfirmationIndicatorVisible() {
        try {
            return homePage.isConfirmationIndicatorVisible(wait);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isStorePersistedAfterNavigation() {
        try {
            driver.navigate().to("https://www.footlocker.com/product/nike-air-force-1-low-mens/42010001.html");
            return homePage.isConfirmationIndicatorVisible(wait);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isEmptyResultsMessageVisible() {
        try {
            return homePage.isEmptyResultsMessageVisible(wait);
        } catch (TimeoutException e) {
            return false;
        }
    }
}
