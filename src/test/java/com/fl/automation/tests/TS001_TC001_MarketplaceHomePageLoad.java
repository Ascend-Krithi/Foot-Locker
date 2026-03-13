package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC001_MarketplaceHomePageLoad extends BaseTest {

    @Test(description = "TC_4139: SCRUM-19509 TS-001 TC-001 - Marketplace home page load")
    public void verifyMarketplaceHomePageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        wait.until(ExpectedConditions.urlContains("footlocker"));
        
        String pageTitle = driver.getTitle();
        Assert.assertFalse(pageTitle.isEmpty(), "Marketplace home page should load successfully");
    }
}