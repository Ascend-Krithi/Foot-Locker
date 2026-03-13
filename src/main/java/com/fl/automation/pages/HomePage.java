package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private static final List<By> HEADER_FIND_A_STORE = Arrays.asList(
        By.linkText("Find a Store"),
        By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']"),
        By.cssSelector("a[href*='store-locator']"),
        By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'find a store')]")
    );
    
    private static final List<By> SELECT_MY_STORE = Arrays.asList(
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store')]")
    );
    
    private static final List<By> COOKIE_ACCEPT = Arrays.asList(
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler")
    );
    
    private static final List<By> MODAL_CLOSE = Arrays.asList(
        By.cssSelector("button[aria-label='Close']"),
        By.xpath("//button[@aria-label='Close' or contains(@class,'close')]")
    );
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void acceptCookies() {
        try {
            WebElement cookieBtn = findElementWithFallback(COOKIE_ACCEPT);
            if (cookieBtn != null) {
                clickWithJsFallback(cookieBtn);
            }
        } catch (Exception e) {
            // Cookie banner not present
        }
    }
    
    public void closeModalIfPresent() {
        try {
            WebElement closeBtn = findElementWithFallback(MODAL_CLOSE);
            if (closeBtn != null) {
                clickWithJsFallback(closeBtn);
            }
        } catch (Exception e) {
            // Modal not present
        }
    }
    
    public void clickFindStore() {
        acceptCookies();
        closeModalIfPresent();
        WebElement findStoreLink = findElementWithFallback(HEADER_FIND_A_STORE);
        if (findStoreLink != null) {
            clickWithJsFallback(findStoreLink);
        } else {
            throw new RuntimeException("Find a Store link not found");
        }
    }
    
    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement element = findElementWithFallback(SELECT_MY_STORE);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }
    
    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}