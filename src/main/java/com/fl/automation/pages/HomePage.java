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

    private By findStoreLink = By.linkText("Find a Store");
    private By findStoreLinkFallback1 = By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']");
    private By findStoreLinkFallback2 = By.cssSelector("a[href*='store-locator']");
    private By findStoreLinkFallback3 = By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'find a store')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store')]");
    private By selectMyStoreLinkFallback = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickFindStore() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink));
            element.click();
        } catch (Exception e1) {
            try {
                WebElement element = driver.findElement(findStoreLinkFallback1);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception e2) {
                try {
                    WebElement element = driver.findElement(findStoreLinkFallback2);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                } catch (Exception e3) {
                    WebElement element = driver.findElement(findStoreLinkFallback3);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                }
            }
        }
    }

    public void clickSelectMyStore() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
            element.click();
        } catch (Exception e) {
            WebElement element = driver.findElement(selectMyStoreLinkFallback);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink)).isDisplayed();
        } catch (Exception e) {
            try {
                return driver.findElement(selectMyStoreLinkFallback).isDisplayed();
            } catch (Exception e2) {
                return false;
            }
        }
    }
}