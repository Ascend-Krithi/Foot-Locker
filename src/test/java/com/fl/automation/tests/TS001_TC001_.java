package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test
    public void testScenario() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isPopupMessageVisible("Choose a preferred store to make shopping easier"), "Popup message not displayed");

        Assert.assertTrue(homePage.isSelectMyStoreVisible(), "'Select My Store' link is not visible");
    }
}