package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.fl.automation.listeners.TestListener.class)
public class TS001_TC001_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-001")
    public void testFindAStorePopupAndSelectMyStoreLink() {
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupDisplayed(), "Popup should appear below 'Find a Store'");
        Assert.assertTrue(homePage.isPopupMessageDisplayed("Choose a preferred store to make shopping easier"), "Popup should display the message 'Choose a preferred store to make shopping easier'");
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "'Select My Store' link should be visible within the popup");
    }
}