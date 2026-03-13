package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS022_TC001_SaveProjectToFavorites extends BaseTest {
    
    @Test(description = "TC4155: SCRUM-19509 TS-014 TC-001 - Save project to favorites")
    public void saveProjectToFavorites() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Save project to favorites");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to project details");
        driver.get(MARKETPLACE_URL + "/project/12345");
        ExtentManager.getTest().log(Status.PASS, "Project details page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Favorite button");
        WebElement favoriteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".favorite-btn")));
        favoriteBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Favorite button clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying project added to favorites");
        WebElement favoriteIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".favorite-icon.active")));
        Assert.assertTrue(favoriteIcon.isDisplayed(), "Favorite icon should be active");
        ExtentManager.getTest().log(Status.PASS, "Project added to favorites");
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to favorites page");
        driver.get(MARKETPLACE_URL + "/favorites");
        ExtentManager.getTest().log(Status.PASS, "Favorites page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying project appears in favorites list");
        WebElement favoriteProject = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".favorite-project-card")));
        Assert.assertTrue(favoriteProject.isDisplayed(), "Project should appear in favorites list");
        ExtentManager.getTest().log(Status.PASS, "Project verified in favorites list");
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}