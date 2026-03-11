package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By[] findStoreCandidates = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private By[] selectMyStoreCandidates = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By storePopup = By.cssSelector(".store-locator-popup, [class*='store-popup'], [class*='store-locator']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    private WebElement findElementWithFallback(By[] candidates) {
        for (By by : candidates) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (TimeoutException ignored) {
            }
        }
        return null;
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public void clickFindStore() {
        WebElement element = findElementWithFallback(findStoreCandidates);
        if (element != null) {
            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }

    public boolean isStorePopupDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storePopup)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement element = findElementWithFallback(selectMyStoreCandidates);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = findElementWithFallback(selectMyStoreCandidates);
        if (element != null) {
            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}