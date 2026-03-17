package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
    }

    // ================= WAIT =================

    public void waitForStoreLocatorToLoad() {
        int retries = 3;

        for (int i = 1; i <= retries; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[role='dialog'], .store-locator-modal")
                ));

                // iframe handling
                List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
                if (!iframes.isEmpty()) {
                    driver.switchTo().frame(iframes.get(0));
                }

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]")
                ));

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(text(),'Search') or contains(.,'Store')]")
                ));

                return;

            } catch (Exception e) {
                System.out.println("[WARN] Retry loading store locator... Attempt: " + i);
                try { Thread.sleep(3000); } catch (Exception ignored) {}
            }
        }

        throw new RuntimeException("Store locator input load failed");
    }

    // ================= ACTIONS =================

    public void enterLocation(String location) {
        WebElement input = driver.findElement(
                By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]")
        );
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton() {
        driver.findElement(
                By.xpath("//button[contains(text(),'Search') or contains(.,'Store')]")
        ).click();
    }

    public void clickSetMyStoreForAddress(String address) {
        driver.findElement(
                By.xpath("//div[contains(text(),'" + address + "')]/ancestor::div[contains(@class,'store')]//button")
        ).click();
    }

    // ================= VALIDATIONS =================

    public boolean isLocationSearchInputDisplayed() {
        try {
            return driver.findElement(
                    By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]")
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            return driver.findElement(
                    By.xpath("//button[contains(text(),'Search') or contains(.,'Store')]")
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            return driver.findElements(
                    By.cssSelector(".store-result, .store-list-item")
            ).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSpecificStoreDisplayed(String address) {
        try {
            return driver.findElement(
                    By.xpath("//*[contains(text(),'" + address + "')]")
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            return driver.findElement(
                    By.xpath("//*[contains(text(),'selected') or contains(text(),'My Store')]")
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
