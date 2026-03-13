package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdminDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By dashboardTitle = By.cssSelector("h1, .dashboard-title");
    private By usersLink = By.linkText("Users");
    private By applicationsLink = By.linkText("Applications");
    private By reportsLink = By.linkText("Reports");
    
    public AdminDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public boolean isDashboardDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(dashboardTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areAdminFeaturesVisible() {
        try {
            return driver.findElement(usersLink).isDisplayed() && 
                   driver.findElement(applicationsLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}