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
    private By findStoreButton = By.xpath(
        "//button[contains(normalize-space(.), 'Find a Store')] | " +
        "//a[contains(normalize-space(.), 'Find a Store')] | " +
        "//div[contains(@class, 'HeaderButton')][contains(normalize-space(.), 'Find a Store')] | " +
        "//span[contains(normalize-space(.), 'Find a Store')]/parent::* | " +
        "//*[contains(@class, 'StoreLocator')][contains(normalize-space(.), 'Find a Store')]"
    );
    private By selectMyStoreLink = By.xpath(
        "//*[contains(@class,'StoreLocatorDropdown')]//*[contains(normalize-space(text()),'Select my store')] | " +
        "//a[contains(normalize-space(text()),'Select my store')] | " +
        "//button[contains(normalize-space(text()),'Select my store')]"
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            clickWithFallback(cookieButton);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Cookie banner not present or already accepted");
        }
    }

    public void clickFindStore() {
        try {
            WebElement findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
            clickWithFallback(findStore);
            Thread.sleep(1500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Find a Store button: " + e.getMessage());
        }
    }

    public void clickSelectMyStore() {
        try {
            WebElement selectStore = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
            clickWithFallback(selectStore);
            Thread.sleep(1500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Select My Store link: " + e.getMessage());
        }
    }

    public boolean isOnFootLockerDomain() {
        try {
            String currentUrl = driver.getCurrentUrl();
            return currentUrl.contains("footlocker.com");
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToSneakersPage() {
        try {
            driver.navigate().to("https://www.footlocker.com/en/category/shoes/sneakers.html");
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to sneakers page: " + e.getMessage());
        }
    }

    private void clickWithFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Both regular and JS click failed: " + jsException.getMessage());
            }
        }
    }
}
