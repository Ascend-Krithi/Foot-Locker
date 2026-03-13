package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC003_EcoHomeHubInstallerSearch extends BaseTest {

    @Test(description = "TC_4106: SCRUM-19509 TS-001 TC-003 - Eco Home Hub installer search")
    public void verifyInstallerSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("input[type='search'], input[placeholder*='search' i]")));
        Assert.assertTrue(searchInput.isDisplayed(), "Search input should be visible");
    }
}