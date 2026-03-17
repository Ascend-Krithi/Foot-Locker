package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // ===== LOCATORS =====
    private By findStoreButton = By.xpath(
            "//a[contains(@href,'store-locator')] | " +
            "//button[contains(text(),'Find a Store')] | " +
            "//span[contains(text(),'Find a Store')]"
    );

    private By storePopupHeader = By.xpath(
            "//*[contains(text(),'Find a Store') or contains(text(),'Store Locator')]"
    );

    private By storeCards = By.xpath("//*[contains(@class,'store') and contains(@class,'card')]");
    private By storeAddress = By.xpath(".//address");
    private By setMyStoreButton = By.xpath(".//button[contains(text(),'Set')]");
    private By storeConfirmationBanner = By.xpath("//*[contains(text(),'My Store')]");

    // ===== COMMON UTIL =====
    private WebElement getElementUsingJS(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript(script);
    }

    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    // ===== STEP 1: OPEN STORE LOCATOR =====
    public void openStoreLocator() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
        safeClick(btn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(storePopupHeader));
        System.out.println("[INFO] Store locator popup opened.");
    }

    // ===== STEP 2: WAIT FOR INPUT (FIXED) =====
    public void waitForStoreLocatorToLoad() {
        try {
            Thread.sleep(3000); // allow UI animation

            WebElement input = null;

            for (int i = 0; i < 10; i++) {
                input = getElementUsingJS(
                        "return document.querySelector('input[placeholder*=\"address\"], input[placeholder*=\"city\"], input[type=\"search\"]');"
                );

                if (input != null && input.isDisplayed()) {
                    System.out.println("[INFO] Store locator input found.");
                    return;
                }

                Thread.sleep(1000);
            }

            throw new RuntimeException("Store locator input not found after retries");

        } catch (Exception e) {
            throw new RuntimeException("Store locator input load failed", e);
        }
    }

    // ===== VALIDATIONS =====
    public boolean isLocationSearchInputDisplayed() {
        try {
            WebElement input = getElementUsingJS(
                    "return document.querySelector('input[placeholder*=\"address\"], input[placeholder*=\"city\"], input[type=\"search\"]');"
            );
            return input != null && input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement btn = getElementUsingJS(
                    "return document.querySelector('button[type=\"submit\"], button');"
            );
            return btn != null && btn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ===== ACTIONS =====
    public void enterLocation(String location) {
        WebElement input = getElementUsingJS(
                "return document.querySelector('input[placeholder*=\"address\"], input[placeholder*=\"city\"], input[type=\"search\"]');"
        );

        if (input == null) {
            throw new RuntimeException("Location input not found");
        }

        input.clear();
        input.sendKeys(location);

        System.out.println("[INFO] Entered location: " + location);
    }

    public void clickSearchButton() {
        WebElement btn = getElementUsingJS(
                "return document.querySelector('button[type=\"submit\"], button');"
        );

        if (btn == null) {
            throw new RuntimeException("Search button not found");
        }

        safeClick(btn);
        System.out.println("[INFO] Clicked search button.");
    }

    // ===== RESULTS =====
    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> cards = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards)
            );
            return cards.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSpecificStoreDisplayed(String addressText) {
        try {
            List<WebElement> cards = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards)
            );

            for (WebElement card : cards) {
                try {
                    String text = card.findElement(storeAddress).getText();
                    if (text.contains(addressText)) {
                        return true;
                    }
                } catch (Exception ignored) {}
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String addressText) {
        List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards)
        );

        for (WebElement card : cards) {
            try {
                String text = card.findElement(storeAddress).getText();
                if (text.contains(addressText)) {
                    WebElement btn = card.findElement(setMyStoreButton);
                    safeClick(btn);
                    return;
                }
            } catch (Exception ignored) {}
        }

        throw new RuntimeException("Store not found: " + addressText);
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(storeConfirmationBanner)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
