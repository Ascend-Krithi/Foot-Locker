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

    private List<By> findStoreLocators = Arrays.asList(
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    );

    private List<By> selectMyStoreLocators = Arrays.asList(
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    );

    private List<By> popupMessageLocators = Arrays.asList(
        By.xpath("//*[contains(normalize-space(.),'Choose a preferred store to make shopping easier.') or contains(normalize-space(.),'Choose a preferred store')]")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickFindStore() {
        WebElement element = waitForAnyElement(findStoreLocators);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean isPopupMessageDisplayed() {
        try {
            WebElement element = waitForAnyElement(popupMessageLocators);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement element = waitForAnyElement(selectMyStoreLocators);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = waitForAnyElement(selectMyStoreLocators);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private WebElement waitForAnyElement(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception ignored) {
            }
        }
        throw new RuntimeException("None of the fallback locators found the element");
    }
}