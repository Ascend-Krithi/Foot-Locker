package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC002() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        homePage.clickSelectMyStore();

        Assert.assertTrue(driver.getPageSource().contains("Location") || driver.getPageSource().contains("Search"), "Store locator popup elements not found");
    }
}