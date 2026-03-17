package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
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

    // 🔥 Locators (robust)
    private By locationInput = By.xpath("//input[contains(@placeholder,'location') or contains(@aria-label,'location')]");
    private By searchButton = By.xpath("//button[.//text()[contains(.,'Search')]]");
    private By storeResults = By.xpath("//div[contains(@class,'store') or contains(@class,'result')]");
    private By setMyStoreButton = By.xpath("//button[contains(.,'Set') or contains(.,'My Store')]");
    private By confirmationMessage = By.xpath("//*[contains(text(),'store') and contains(text(),'set')]");

    // ✅ Wait for popup
    public void waitForStoreLocatorToLoad() {
        int retries = 3;

        for (int i = 1; i <= retries; i++) {
            try {
                System.out.println("[INFO] Waiting for store locator modal... Attempt: " + i);

                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("input")));
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));

                if (input.isDisplayed()) {
                    System.out.println("[INFO] Store locator loaded successfully.");
                    return;
                }

            } catch (Exception e) {
                System.out.println("[WARN] Retry loading store locator... Attempt: " + i);
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("Store locator input load failed");
    }

    // ✅ Enter location
    public void enterLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        input.clear();
        input.sendKeys(location);
        input.sendKeys(Keys.ENTER);
        System.out.println("[INFO] Entered location: " + location);
    }

    // ✅ Click search
    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        System.out.println("[INFO] Clicked search button");
    }

    // ✅ Validate results
    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(storeResults));
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Validate specific store
    public boolean isSpecificStoreDisplayed(String storeText) {
        try {
            return driver.findElement(By.xpath("//*[contains(text(),'" + storeText + "')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Click Set My Store
    public void clickSetMyStoreForAddress(String storeText) {
        try {
            WebElement store = driver.findElement(By.xpath("//*[contains(text(),'" + storeText + "')]/ancestor::div"));
            WebElement btn = store.findElement(By.xpath(".//button[contains(.,'Set')]"));
            btn.click();
            System.out.println("[INFO] Clicked Set My Store for: " + storeText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Set My Store for: " + storeText);
        }
    }

    // ✅ Confirmation
    public boolean isStoreConfirmationDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Existing validations (your TC002)
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
