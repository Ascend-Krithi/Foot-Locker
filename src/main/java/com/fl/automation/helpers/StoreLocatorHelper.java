package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // 🔥 Flexible locators
    private By locationInput = By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]");
    private By searchButton = By.xpath("//button[contains(.,'Search') or contains(.,'Stores')]");
    private By storeResults = By.xpath("//div[contains(@class,'store') or contains(@class,'result')]");
    private By confirmationMessage = By.xpath("//*[contains(text(),'store') and contains(text(),'set')]");

    // ✅ Wait for popup
    public void waitForStoreLocatorToLoad() {
        int attempts = 0;

        while (attempts < 3) {
            try {
                driver.switchTo().defaultContent();

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

    // ✅ Enter location
    public void enterLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        input.clear();
        input.sendKeys(location);
        System.out.println("[INFO] Entered location: " + location);
    }

    // ✅ Click search
    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        System.out.println("[INFO] Clicked search button.");
    }

    // ✅ Validate results
    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults));
            return driver.findElements(storeResults).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Validate specific store
    public boolean isSpecificStoreDisplayed(String storeName) {
        try {
            return driver.getPageSource().toLowerCase().contains(storeName.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Click "Set My Store"
    public void clickSetMyStoreForAddress(String storeName) {
        try {
            By setStoreBtn = By.xpath("//*[contains(text(),'" + storeName + "')]/following::button[contains(.,'Set') or contains(.,'Store')][1]");
            wait.until(ExpectedConditions.elementToBeClickable(setStoreBtn)).click();
            System.out.println("[INFO] Clicked Set My Store for: " + storeName);
        } catch (Exception e) {
            throw new RuntimeException("Unable to click Set My Store for: " + storeName);
        }
    }

    // ✅ Confirmation validation
    public boolean isStoreConfirmationDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Existing validations
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
