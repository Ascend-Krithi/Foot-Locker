package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC001() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(driver.getPageSource().contains("Select My Store") || driver.getPageSource().contains("Choose a preferred store"), "Select My Store link or popup message not found");
    }
}