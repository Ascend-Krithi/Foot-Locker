package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Wait for Store Locator modal + elements to fully load (CI safe)
     */
    public void waitForStoreLocatorToLoad() {
        int retries = 3;

        for (int i = 1; i <= retries; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

                // 🔥 Wait for modal/dialog to appear
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[role='dialog'], .store-locator-modal")
                ));

                // 🔥 Handle iframe if present
                List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
                if (!iframes.isEmpty()) {
                    driver.switchTo().frame(iframes.get(0));
                    System.out.println("[INFO] Switched to iframe");
                }

                // 🔥 Wait for location input field
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]")
                ));

                // 🔥 Wait for search button
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(text(),'Search') or contains(.,'Store')]")
                ));

                System.out.println("[INFO] Store locator loaded successfully");
                return;

            } catch (Exception e) {
                System.out.println("[WARN] Retry loading store locator... Attempt: " + i);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("Store locator input load failed");
    }

    /**
     * Verify location search input is displayed
     */
    public boolean isLocationSearchInputDisplayed() {
        try {
            return driver.findElement(
                    By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]")
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify search button is displayed
     */
    public boolean isSearchButtonDisplayed() {
        try {
            return driver.findElement(
                    By.xpath("//button[contains(text(),'Search') or contains(.,'Store')]")
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
