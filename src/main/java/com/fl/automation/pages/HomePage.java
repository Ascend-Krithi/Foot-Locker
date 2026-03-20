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

    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");
    private By findStoreLink = By.xpath("//*[contains(@class,'StoreLocatorDropdown')]");
    private By selectMyStoreLink = By.xpath("//*[contains(@class,'StoreLocatorDropdown')]//*[contains(normalize-space(text()),'Select my store')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieButton.click();
            Thread.sleep(500);
        } catch (Exception e) {
            // Cookie banner not present, continue
        }
    }

    public void clickFindStore() {
        try {
            WebElement findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink));
            findStore.click();
            Thread.sleep(500);
        } catch (Exception e) {
            WebElement findStore = driver.findElement(findStoreLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", findStore);
        }
    }

    public void clickSelectMyStore() {
        try {
            WebElement selectStore = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
            selectStore.click();
            Thread.sleep(500);
        } catch (Exception e) {
            WebElement selectStore = driver.findElement(selectMyStoreLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectStore);
        }
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}