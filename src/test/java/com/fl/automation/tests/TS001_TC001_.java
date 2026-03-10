package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {
    @Test
    public void testScenario() {
        HomePage home = new HomePage(driver);
        // Step 1: Verify homepage loads
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Homepage title should contain 'Foot Locker'");
        // Step 2: Click 'Find a Store', verify popup appears with message
        home.clickFindStore();
        org.openqa.selenium.WebElement popup = driver.findElement(org.openqa.selenium.By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]"));
        Assert.assertTrue(popup.isDisplayed(), "Popup with correct message should appear");
        // Step 3: Check 'Select My Store' link is visible in popup
        Assert.assertTrue(home.isSelectMyStoreVisible(), "'Select My Store' link should be visible in popup");
    }
}
