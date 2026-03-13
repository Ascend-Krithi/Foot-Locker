package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS009_TC001_EditProfile extends BaseTest {

    @Test(description = "TC_4149: SCRUM-19509 TS-009 TC-001 - Edit profile")
    public void verifyEditProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        driver.get(baseUrl);
        
        WebElement profileLink = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("a[href*='profile'], a[href*='account']")));
        Assert.assertTrue(profileLink.isDisplayed(), "Edit profile functionality should be available");
    }
}