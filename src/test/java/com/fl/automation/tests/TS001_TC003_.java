package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {
    @Test
    public void testScenario() {
        HomePage home = new HomePage(driver);
        // Step 1: Verify homepage loads
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Homepage title should contain 'Foot Locker'");
        // Step 2: Click 'Find a Store', verify popup appears
        home.clickFindStore();
        // Step 3: Click 'Select My Store', verify store locator popup opens
        home.clickSelectMyStore();
        // Step 4: Enter 'Boston, MA' in Location textbox
        home.enterLocation("Boston, MA");
        // Step 5: Click 'Search for Stores', verify search results displayed with stores in/near Boston
        home.clickSearchButton();
        Assert.assertTrue(home.areStoreResultsDisplayed(), "Search results should be displayed for Boston, MA");
    }
}
