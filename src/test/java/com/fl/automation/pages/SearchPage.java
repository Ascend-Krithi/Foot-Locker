package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By resultsContainer = By.cssSelector(".search-results, .results-container");
    private By noResultsMessage = By.cssSelector(".no-results");
    
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public boolean areResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(resultsContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isNoResultsMessageDisplayed() {
        try {
            return driver.findElement(noResultsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}