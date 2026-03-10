package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {
    @Test
    public void testScenario() {
        HomePage home = new HomePage(driver);
        // Step 1: Verify homepage loads
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Homepage title should contain 'Foot Locker'");
        // Step 2: Click 'Find a Store', verify popup appears
        home.clickFindStore();
        // Step 3: Click 'Select My Store', verify store locator popup opens
        home.clickSelectMyStore();
        // Step 4: Check for 'Location' textbox and 'Search for Stores' button
        Assert.assertTrue(home.isSearchInputVisible(), "Location textbox should be visible");
        Assert.assertTrue(home.isSearchButtonVisible(), "Search for Stores button should be visible");
    }
}
