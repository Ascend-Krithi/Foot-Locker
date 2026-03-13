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

    // Locators with fallback strategy
    private static final List<By> HEADER_FIND_A_STORE = Arrays.asList(
        By.linkText("Find a Store"),
        By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']"),
        By.cssSelector("a[href*='store-locator']"),
        By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'find a store')]")
    );

    private static final List<By> COOKIE_ACCEPT = Arrays.asList(
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler"),
        By.cssSelector("button[aria-label*='Accept' i]"),
        By.xpath("//button[contains(normalize-space(.),'Accept All Cookies') or contains(normalize-space(.),'Accept Cookies')]")
    );

    private static final List<By> POPUP_MESSAGE = Arrays.asList(
        By.xpath("//*[contains(text(),'Choose a preferred store')]"),
        By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]"),
        By.cssSelector("[class*='store-popup'] [class*='message']")
    );

    private static final List<By> SELECT_MY_STORE_LINK = Arrays.asList(
        By.linkText("Select My Store"),
        By.xpath("//a[normalize-space()='Select My Store']"),
        By.cssSelector("a[href*='store-locator']"),
        By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select my store')]")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void acceptCookies() {
        try {
            WebElement cookieButton = findElementWithFallback(COOKIE_ACCEPT, 10);
            if (cookieButton != null) {
                clickElement(cookieButton);
            }
        } catch (Exception e) {
            // Cookie banner not present, continue
        }
    }

    public void clickFindAStore() {
        WebElement findStoreLink = findElementWithFallback(HEADER_FIND_A_STORE, 40);
        clickElement(findStoreLink);
    }

    public boolean isPopupMessageDisplayed() {
        try {
            WebElement message = findElementWithFallback(POPUP_MESSAGE, 20);
            return message != null && message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        try {
            WebElement link = findElementWithFallback(SELECT_MY_STORE_LINK, 20);
            return link != null && link.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement selectMyStoreLink = findElementWithFallback(SELECT_MY_STORE_LINK, 40);
        clickElement(selectMyStoreLink);
    }

    private WebElement findElementWithFallback(List<By> locators, int timeoutSeconds) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        
        for (By locator : locators) {
            try {
                return shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                // Try next locator
            }
        }
        
        throw new RuntimeException("Element not found with any of the fallback locators");
    }

    private void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}