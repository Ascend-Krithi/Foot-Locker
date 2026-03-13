package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS005_TC001_ProductionDeployment extends BaseTest {

    @Test(description = "TC_4115: SCRUM-19509 TS-005 TC-001 - Production deployment")
    public void verifyProductionDeployment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        wait.until(ExpectedConditions.urlContains("footlocker"));
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker"), "Production site should be accessible");
    }
}