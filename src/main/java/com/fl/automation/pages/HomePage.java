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

    private final List<By> HEADER_FIND_A_STORE = Arrays.asList(
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store')]")
    );

    private final List<By> SELECT_MY_STORE = Arrays.asList(
        By.xpath("//a[contains(.,'Select My Store')]"),
        By.xpath("//button[contains(.,'Select My Store')]")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.urlContains("footlocker"));
    }

    public void clickFindAStore() {
        WebElement element = findElementWithFallback(HEADER_FIND_A_STORE);
        clickWithJSFallback(element);
    }

    public boolean isFindAStoreDisplayed() {
        try {
            WebElement element = findElementWithFallback(HEADER_FIND_A_STORE);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = findElementWithFallback(SELECT_MY_STORE);
        clickWithJSFallback(element);
    }

    public boolean isSelectMyStoreDisplayed() {
        try {
            WebElement element = findElementWithFallback(SELECT_MY_STORE);
            return element.isDisplayed();
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
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickWithJSFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}