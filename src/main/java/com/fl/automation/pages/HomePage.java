package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By[] findStoreLinkLocators = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store')]")
    };

    private By[] selectMyStoreLinkLocators = {
        By.xpath("//a[contains(.,'Select My Store')]"),
        By.xpath("//button[contains(.,'Select My Store')]")
    };

    private By cookieAcceptLocator = By.id("onetrust-accept-btn-handler");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptLocator));
            cookieButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Cookie consent not present or already accepted");
        }
    }

    public void clickFindStore() {
        WebElement findStoreLink = findElementWithFallback(findStoreLinkLocators);
        wait.until(ExpectedConditions.elementToBeClickable(findStoreLink));
        try {
            findStoreLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", findStoreLink);
        }
    }

    public boolean isFindStoreLinkDisplayed() {
        try {
            WebElement findStoreLink = findElementWithFallback(findStoreLinkLocators);
            return findStoreLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        try {
            WebElement selectMyStoreLink = findElementWithFallback(selectMyStoreLinkLocators);
            return wait.until(ExpectedConditions.visibilityOf(selectMyStoreLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement selectMyStoreLink = findElementWithFallback(selectMyStoreLinkLocators);
        wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
        try {
            selectMyStoreLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectMyStoreLink);
        }
    }

    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }
}