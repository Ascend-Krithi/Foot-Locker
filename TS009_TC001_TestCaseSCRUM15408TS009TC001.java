package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS009_TC001_TestCaseSCRUM15408TS009TC001 extends BaseTest {
    @Test
    public void testInvalidLocationSearch() {
        test = extent.createTest("TS009_TC001_TestCaseSCRUM15408TS009TC001");
        HomePage homePage = new HomePage(driver);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        StoreResultsPage resultsPage = new StoreResultsPage(driver);

        test.info("Step 1: Launch the Foot Locker homepage in a browser.");
        homePage.open("https://www.footlocker.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Homepage title validation");

        test.info("Step 2: Click on the 'Find a Store' link/button.");
        homePage.clickFindAStore();

        test.info("Step 3: Click on the 'Select My Store' link.");
        locatorPage.clickSelectMyStore();

        test.info("Step 4: Enter 'Invalid Location' in the Location textbox.");
        locatorPage.enterLocation("Invalid Location");
        Assert.assertEquals(locatorPage.getSearchInput().getAttribute("value"), "Invalid Location");

        test.info("Step 5: Click the 'Search for Stores' button.");
        locatorPage.clickSearchButton();
        Assert.assertTrue(resultsPage.getEmptyResultsMessage() != null || resultsPage.getStoreCards().size() == 0, "No results or error message displayed");
    }
}