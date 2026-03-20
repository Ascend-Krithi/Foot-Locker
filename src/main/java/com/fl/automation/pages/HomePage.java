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
    private JavascriptExecutor js;

    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");
    private By findStoreButton = By.cssSelector("button[data-testid='find-a-store-button']");
    private By findStoreLink = By.xpath("//a[contains(text(),'Find a Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(text(),'Select My Store')]");
    private By selectMyStoreLink = By.xpath("//a[contains(text(),'Select My Store')]");
    private By storeLocatorPopup = By.cssSelector("div[data-testid='store-locator-popup']");
    private By storeLocatorModal = By.cssSelector("div[role='dialog']");
    private By sneakersLink = By.xpath("//a[contains(@href,'/sneakers')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.js = (JavascriptExecutor) driver;
    }

    public void acceptCookies() {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            acceptButton.click();
        } catch (Exception e) {
            System.out.println("Cookie banner not displayed or already accepted");
        }
    }

    public void clickFindStore() {
        try {
            WebElement findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
            findStore.click();
        } catch (Exception e) {
            try {
                WebElement findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink));
                js.executeScript("arguments[0].click();", findStore);
            } catch (Exception ex) {
                throw new RuntimeException("Unable to click Find Store button");
            }
        }
    }

    public void clickSelectMyStore() {
        try {
            WebElement selectStore = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreButton));
            selectStore.click();
        } catch (Exception e) {
            try {
                WebElement selectStore = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
                js.executeScript("arguments[0].click();", selectStore);
            } catch (Exception ex) {
                throw new RuntimeException("Unable to click Select My Store button");
            }
        }
    }

    public boolean isStoreLocatorPopupDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeLocatorPopup)).isDisplayed();
        } catch (Exception e) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(storeLocatorModal)).isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isOnFootLockerDomain() {
        return driver.getCurrentUrl().contains("footlocker.com");
    }

    public void navigateToSneakersPage() {
        try {
            WebElement sneakers = wait.until(ExpectedConditions.elementToBeClickable(sneakersLink));
            sneakers.click();
        } catch (Exception e) {
            driver.get("https://www.footlocker.com/sneakers");
        }
    }
}