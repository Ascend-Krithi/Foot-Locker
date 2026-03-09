package com.fl.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait waitShort;
    private final WebDriverWait wait;

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
            } catch (TimeoutException ignored) { }
            catch (Exception ignored) { }
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
            } catch (TimeoutException ignored) { }
            catch (Exception ignored) { }
        }
    }

    public void clickFindAStore() {
        try { acceptCookiesIfPresent(); } catch (Exception ignored) {}
        try { closeFlxRewardsIfPresent(); } catch (Exception ignored) {}

        By[] candidates = new By[] {
            By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']"),
            By.xpath("//a[contains(@href,'store-locator')]"),
            By.xpath("//button[contains(.,'Find a Store') or contains(.,'Find a store')]"),
            By.xpath("//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'find a store')]")
        };

        WebElement target = null;
        for (By by : candidates) {
            try {
                target = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
            } catch (TimeoutException ignored) { }
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Find a Store' control using known locators.");
        }

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", target);
            wait.until(ExpectedConditions.elementToBeClickable(target)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }
}