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
import java.util.Set;

public class TS021_TC002_ShareProjectViaSocialMedia extends BaseTest {
    
    @Test(description = "TC4154: SCRUM-19509 TS-013 TC-002 - Share project via social media")
    public void shareProjectViaSocialMedia() {
        ExtentManager.getTest().log(Status.INFO, "Starting test: Share project via social media");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        ExtentManager.getTest().log(Status.INFO, "Navigating to project details");
        driver.get(MARKETPLACE_URL + "/project/12345");
        ExtentManager.getTest().log(Status.PASS, "Project details page loaded");
        
        ExtentManager.getTest().log(Status.INFO, "Clicking Share button");
        WebElement shareBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".share-btn")));
        shareBtn.click();
        ExtentManager.getTest().log(Status.PASS, "Share menu opened");
        
        ExtentManager.getTest().log(Status.INFO, "Selecting Twitter option");
        String mainWindow = driver.getWindowHandle();
        WebElement twitterOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".share-twitter")));
        twitterOption.click();
        ExtentManager.getTest().log(Status.PASS, "Twitter share option clicked");
        
        ExtentManager.getTest().log(Status.INFO, "Verifying social media share window opened");
        Set<String> allWindows = driver.getWindowHandles();
        boolean newWindowOpened = allWindows.size() > 1;
        Assert.assertTrue(newWindowOpened, "Social media share window should open");
        ExtentManager.getTest().log(Status.PASS, "Social media share window opened");
        
        if (newWindowOpened) {
            for (String window : allWindows) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    driver.close();
                }
            }
            driver.switchTo().window(mainWindow);
            ExtentManager.getTest().log(Status.INFO, "Closed social media window and returned to main window");
        }
        
        ExtentManager.getTest().log(Status.INFO, "Test completed successfully");
    }
}