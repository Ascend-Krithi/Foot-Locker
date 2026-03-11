package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC001() {
        HomePage homePage = new HomePage(driver);

        homePage.clickFindStore();
        Assert.assertTrue(homePage.isStorePopupDisplayed(), "A popup should appear below 'Find a Store' displaying the message 'Choose a preferred store to make shopping easier'");

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "'Select My Store' link should be visible within the popup");
    }
}