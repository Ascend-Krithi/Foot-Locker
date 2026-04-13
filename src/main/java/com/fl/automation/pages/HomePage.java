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
    private By mobilesMenu = By.xpath("//a[contains(text(),'Mobiles')]");
    private By allPhonesOption = By.xpath("//a[contains(text(),'All Phones')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }

    public void acceptCookies() {
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already accepted");
        }
    }

    public void clickMobilesMenu() {
        WebElement mobilesMenuElement = wait.until(ExpectedConditions.elementToBeClickable(mobilesMenu));
        try {
            mobilesMenuElement.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mobilesMenuElement);
        }
    }

    public void clickAllPhones() {
        WebElement allPhonesElement = wait.until(ExpectedConditions.elementToBeClickable(allPhonesOption));
        try {
            allPhonesElement.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allPhonesElement);
        }
    }

    public void clickFindStore() {
        // Placeholder for store locator functionality
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("starhub.com");
    }
}