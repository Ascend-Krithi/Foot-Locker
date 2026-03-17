package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // 🔥 Updated locators (more flexible)
    private By locationInput = By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]");
    private By searchButton = By.xpath("//button[contains(.,'Search') or contains(.,'Stores')]");

    // 🔥 CRITICAL FIX: Robust wait with retry
    public void waitForStoreLocatorToLoad() {
        int attempts = 0;

        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
                wait.until(ExpectedConditions.elementToBeClickable(searchButton));

                System.out.println("[INFO] Store locator fully loaded.");
                return;

            } catch (Exception e) {
                attempts++;
                System.out.println("[WARN] Retry loading store locator... Attempt: " + attempts);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("Store locator input load failed");
    }

    public boolean isLocationSearchInputDisplayed() {
        try {
            return driver.findElement(locationInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            return driver.findElement(searchButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
