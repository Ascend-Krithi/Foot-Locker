package com.fl.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait waitShort;
    private final WebDriverWait wait;

    private final By[] FIND_STORE_LOCATORS = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitShort = new WebDriverWait(driver, Duration.ofSeconds(8));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
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
        for (By by : FIND_STORE_LOCATORS) {
            try {
                target = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
            } catch (TimeoutException ignored) {}
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Find a Store' control using STRICT LOCATOR POLICY.");
        }

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", target);
            wait.until(ExpectedConditions.elementToBeClickable(target)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public boolean isSelectMyStoreLinkVisible(){
        By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPopupDisplayed(){
        By[] popupCandidates = new By[] {
            By.xpath("//*[contains(.,'Choose a preferred store to make shopping easier')]"),
            By.xpath("//div[contains(@class,'popup') or contains(@class,'modal') or contains(@class,'drawer')]"),
            By.cssSelector("[role='dialog'], [class*='popup'], [class*='modal']")
        };
        for (By by : popupCandidates) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(by));
                return true;
            } catch (TimeoutException ignored) {}
        }
        return false;
    }
}