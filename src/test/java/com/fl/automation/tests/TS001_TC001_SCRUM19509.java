package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class TS001_TC001_SCRUM19509 extends BaseTest {
    
    @Test(description = "SCRUM-19509 TS-001: Eco Home Hub - Verify homepage loads")
    public void testEcoHomeHubHomepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        wait.until(ExpectedConditions.titleContains("Foot Locker"));
        
        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage should load successfully");
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker.com"), "URL should contain footlocker.com");
    }
}