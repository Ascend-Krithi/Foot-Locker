package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By dashboardTitle = By.cssSelector("h1, .dashboard-title");
    private By profileLink = By.linkText("Profile");
    private By logoutLink = By.linkText("Logout");
    
    public DashboardPage(WebDriver driver) {
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
    
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}