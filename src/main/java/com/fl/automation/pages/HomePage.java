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

    // ===== LOCATORS =====
    private By findStoreButton = By.xpath("//span[contains(text(),'Find a Store')]");
    private By selectMyStoreText = By.xpath("//*[contains(text(),'Select my store')]");

    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");

    // ===== CONSTRUCTOR =====
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ===== HANDLE COOKIE POPUP =====
    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(cookieAcceptButton)
            );
            cookieBtn.click();

            // wait until popup disappears
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieAcceptButton));

        } catch (Exception e) {
            System.out.println("Cookie popup not present or already handled");
        }
    }

    // ===== VALIDATION =====
    public boolean isFindStoreLinkDisplayed() {
        try {
            WebElement findStore = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(findStoreButton)
            );
            return findStore.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ===== ACTION =====
    public void clickFindStore() {
        try {
            WebElement findStore = wait.until(
                    ExpectedConditions.elementToBeClickable(findStoreButton)
            );

            safeClick(findStore);

            // wait for popup to open
            wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreText));

        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Find a Store' and open popup", e);
        }
    }

    // ===== UTIL METHOD =====
    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
