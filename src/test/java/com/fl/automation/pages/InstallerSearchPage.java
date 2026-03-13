package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InstallerSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By locationInput = By.id("location");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By resultsContainer = By.cssSelector(".installer-results");
    
    public InstallerSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void searchInstallers(String location) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locationInput)).sendKeys(location);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        clickWithJsFallback(button);
    }
    
    public boolean areResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(resultsContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    private void clickWithJsFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}