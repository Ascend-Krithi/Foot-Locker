package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InstallerPortalPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By portalTitle = By.cssSelector("h1, .portal-title");
    private By projectsLink = By.linkText("Projects");
    private By profileLink = By.linkText("Profile");
    
    public InstallerPortalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public boolean isPortalDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(portalTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areInstallerFeaturesVisible() {
        try {
            return driver.findElement(projectsLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}