package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MarketplaceHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By searchInput = By.id("search");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By loginLink = By.linkText("Login");
    private By registerLink = By.linkText("Register");
    
    public MarketplaceHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void searchProjects(String query) {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInput)).sendKeys(query);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        clickWithJsFallback(button);
    }
    
    public void clickLogin() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        clickWithJsFallback(element);
    }
    
    public boolean isHomePageDisplayed() {
        return driver.getTitle().contains("Marketplace") || driver.getCurrentUrl().contains("marketplace");
    }
    
    private void clickWithJsFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}