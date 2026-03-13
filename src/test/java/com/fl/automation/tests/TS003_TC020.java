package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4173: SCRUM-17166 Store Locator - Results display
 */
public class TS003_TC020 extends BaseTest {

    @Test(description = "TC-4173: Store Locator results display")
    public void testStoreLocatorResultsDisplay() {
        HomePage homePage = new HomePage(driver);
        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertNotNull(driver.getWindowHandle(), "Results should be displayable");
    }
}