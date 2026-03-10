package com.fl.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait waitShort;
    private final WebDriverWait wait;

    private final By[] findStoreLocators = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private final By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitShort = new WebDriverWait(driver, Duration.ofSeconds(8));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void acceptCookiesIfPresent() {
        By[] cookieCandidates = new By[] {
            By.id("onetrust-accept-btn-handler"),
            By.cssSelector("button#onetrust-accept-btn-handler"),
            By.xpath("//button[contains(.,'Accept All Cookies') or contains(.,'Accept Cookies') or contains(.,'I Accept')]"),
            By.xpath("//div[contains(@class,'cookie') or contains(@id,'cookie')]//button[contains(.,'Accept')]")
        };
        for (By by : cookieCandidates) {
            try {
                WebElement btn = waitShort.until(ExpectedConditions.visibilityOfElementLocated(by));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                break;
            } catch (TimeoutException ignored) {}
            catch (Exception ignored) {}
        }
    }

    public void closeFlxRewardsIfPresent() {
        By[] closeCandidates = new By[] {
            By.xpath("//button[@aria-label='Close' or contains(@class,'close')]"),
            By.xpath("//div[contains(@class,'modal') or contains(@id,'modal')]//button[contains(@aria-label,'Close')]"),
            By.cssSelector("button[aria-label='Close']")
        };
        for (By by : closeCandidates) {
            try {
                WebElement btn = waitShort.until(ExpectedConditions.visibilityOfElementLocated(by));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                break;
            } catch (TimeoutException ignored) {}
            catch (Exception ignored) {}
        }
    }

    public void clickFindAStore() {
        try { acceptCookiesIfPresent(); } catch (Exception ignored) {}
        try { closeFlxRewardsIfPresent(); } catch (Exception ignored) {}

        WebElement target = null;
        for (By by : findStoreLocators) {
            try {
                target = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
            } catch (TimeoutException ignored) {}
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Find a Store' control using policy locators.");
        }

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", target);
            wait.until(ExpectedConditions.elementToBeClickable(target)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public void clickSelectMyStore() {
        WebElement target = null;
        for (By by : selectMyStoreLocators) {
            try {
                target = wait.until(ExpectedConditions.elementToBeClickable(by));
                break;
            } catch (TimeoutException ignored) {}
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Select My Store' button using policy locators.");
        }

        try {
            target.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        for (By by : selectMyStoreLocators) {
            try {
                WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                return elem.isDisplayed();
            } catch (TimeoutException ignored) {}
        }
        return false;
    }

    public boolean isFindAStorePopupDisplayed() {
        By[] popupLocators = new By[] {
            By.xpath("//*[contains(.,'Choose a preferred store to make shopping easier')]"),
            By.xpath("//*[contains(.,'Select My Store')]"),
            By.cssSelector("[class*='store-popup'], [class*='store-locator-popup']")
        };
        for (By by : popupLocators) {
            try {
                WebElement elem = waitShort.until(ExpectedConditions.visibilityOfElementLocated(by));
                return elem.isDisplayed();
            } catch (TimeoutException ignored) {}
        }
        return false;
    }
}