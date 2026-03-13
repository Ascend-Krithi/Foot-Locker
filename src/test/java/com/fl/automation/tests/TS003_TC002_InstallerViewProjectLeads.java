package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS003_TC002_InstallerViewProjectLeads extends BaseTest {

    @Test(description = "TC_4112: SCRUM-19509 TS-003 TC-002 - Installer view project leads")
    public void verifyInstallerViewProjectLeads() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl + "/installer/leads");
        
        WebElement leadsSection = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("section[class*='leads'], div[class*='project']")));
        Assert.assertTrue(leadsSection.isDisplayed(), "Project leads section should be visible");
    }
}