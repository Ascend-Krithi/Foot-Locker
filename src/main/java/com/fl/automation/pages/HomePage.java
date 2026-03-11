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

    private By findStoreLinkText = By.linkText("Find a Store");
    private By findStoreCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    private By selectMyStoreXpath1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreXpath2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.urlContains("footlocker.com"));
    }

    public void clickFindStore() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLinkText));
            element.click();
        } catch (Exception e) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findStoreCss));
                element.click();
            } catch (Exception e2) {
                try {
                    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findStoreXpath));
                    element.click();
                } catch (Exception e3) {
                    WebElement element = driver.findElement(findStoreLinkText);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                }
            }
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreXpath1));
            return true;
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreXpath2));
                return true;
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public void clickSelectMyStore() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreXpath1));
            element.click();
        } catch (Exception e) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreXpath2));
                element.click();
            } catch (Exception e2) {
                WebElement element = driver.findElement(selectMyStoreXpath1);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}