package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");
    private By findStoreLink = By.cssSelector("a[data-testid='find-store-link']");
    private By selectMyStoreLink = By.cssSelector("a[data-testid='select-my-store-link']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Cookie banner not present or already accepted");
        }
    }

    public void clickFindStore() {
        WebElement findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink));
        findStore.click();
    }

    public void clickSelectMyStore() {
        WebElement selectStore = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
        selectStore.click();
    }

    public boolean isFindStoreLinkDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(findStoreLink)).isDisplayed();
        } catch (Exception e) {
            return false;
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