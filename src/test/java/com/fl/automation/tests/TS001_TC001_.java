package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-001")
    public void testFindAStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);

        homePage.navigateToHomePage("https://www.footlocker.com");
        Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage did not load successfully");

        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupVisible(), "Popup did not appear below 'Find a Store'");

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "'Select My Store' link is not visible within the popup");
    }
}
