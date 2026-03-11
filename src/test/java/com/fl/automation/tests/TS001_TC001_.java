package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC001() {
        HomePage homePage = new HomePage(driver);

        homePage.navigateToHomePage("https://www.footlocker.com");
        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Foot Locker") || pageTitle.contains("Sneakers"),
                "Homepage did not load correctly. Expected title to contain 'Foot Locker' or 'Sneakers', but got: " + pageTitle);

        homePage.clickFindStore();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(homePage.isStorePopupDisplayed() || homePage.isSelectMyStoreLinkVisible(),
                "Popup did not appear below 'Find a Store'");

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(),
                "'Select My Store' link is not visible within the popup");
    }
}