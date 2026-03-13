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
    private JavascriptExecutor jsExecutor;

    private static final List<By> HEADER_FIND_A_STORE = Arrays.asList(
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store')]")
    );

    private static final List<By> SELECT_MY_STORE = Arrays.asList(
        By.xpath("//a[contains(.,'Select My Store')]"),
        By.xpath("//button[contains(.,'Select My Store')]")
    );

    private static final List<By> COOKIE_ACCEPT = Arrays.asList(
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public void acceptCookies() {
        try {
            WebElement cookieBtn = findElementWithFallback(COOKIE_ACCEPT);
            if (cookieBtn != null) {
                clickWithJsFallback(cookieBtn);
            }
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already accepted");
        }
    }

    public void clickFindStore() {
        WebElement findStoreLink = findElementWithFallback(HEADER_FIND_A_STORE);
        clickWithJsFallback(findStoreLink);
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement selectMyStore = findElementWithFallback(SELECT_MY_STORE);
            return selectMyStore != null && selectMyStore.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element != null) {
                    return element;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the fallback locators");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            jsExecutor.executeScript("arguments[0].click();", element);
        }
    }
}