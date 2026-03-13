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
            By.cssSelector("header a[href*='stores.footlocker.com']"),
            By.xpath("//header//a[contains(.,'Find a Store')]")
    );

    private static final List<By> SELECT_MY_STORE = Arrays.asList(
            By.xpath("//a[contains(.,'Select My Store')]"),
            By.xpath("//button[contains(.,'Select My Store')]"),
            By.cssSelector("a[href*='select-store']"),
            By.cssSelector("button[class*='select-store']")
    );

    private static final List<By> STORE_POPUP = Arrays.asList(
            By.cssSelector("div[class*='store-locator']"),
            By.cssSelector("div[role='dialog']"),
            By.xpath("//div[contains(@class,'modal')]")
    );

    private static final List<By> STORE_CONFIRMATION = Arrays.asList(
            By.cssSelector("div[class*='store-selected']"),
            By.xpath("//div[contains(.,'Your Store')]"),
            By.cssSelector("span[class*='preferred-store']")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToFootLocker() {
        driver.get("https://www.footlocker.com");
        wait.until(ExpectedConditions.urlContains("footlocker.com"));
    }

    public void clickFindAStore() {
        WebElement element = findElementWithFallback(HEADER_FIND_A_STORE);
        clickWithJsFallback(element);
    }

    public boolean isStorePopupDisplayed() {
        try {
            WebElement popup = findElementWithFallback(STORE_POPUP);
            return popup.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = findElementWithFallback(SELECT_MY_STORE);
        clickWithJsFallback(element);
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            WebElement confirmation = findElementWithFallback(STORE_CONFIRMATION);
            return confirmation.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getStoreConfirmationText() {
        WebElement confirmation = findElementWithFallback(STORE_CONFIRMATION);
        return confirmation.getText();
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}