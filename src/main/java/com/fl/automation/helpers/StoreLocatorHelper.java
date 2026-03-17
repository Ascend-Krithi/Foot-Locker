package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    // 🔥 Updated robust locators (less flaky)
    private By locationInput = By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]");
    private By searchButton = By.xpath("//button[.//text()[contains(.,'Search')]]");

    /**
     * 🔥 CRITICAL FIX: More stable wait logic for CI
     */
    public void waitForStoreLocatorToLoad() {
        int retries = 3;

        for (int i = 1; i <= retries; i++) {
            try {
                System.out.println("[INFO] Waiting for store locator modal... Attempt: " + i);

                // Wait for ANY input first (more generic)
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("input")));

                // Then wait specifically for our field
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));

                if (input.isDisplayed()) {
                    System.out.println("[INFO] Store locator loaded successfully.");
                    return;
                }

            } catch (Exception e) {
                System.out.println("[WARN] Retry loading store locator... Attempt: " + i);
                try {
                    Thread.sleep(2000); // small retry delay
                } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("Store locator input load failed");
    }

    public boolean isLocationSearchInputDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
