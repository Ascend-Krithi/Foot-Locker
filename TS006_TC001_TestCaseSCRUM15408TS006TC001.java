package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC001_TestCaseSCRUM15408TS006TC001 extends BaseTest {
    @Test
    public void testSetMyStoreBoston() {
        test = extent.createTest("TS006_TC001_TestCaseSCRUM15408TS006TC001");
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

        test.info("Step 4: Enter '375 Washington Street, Boston, MA 02108' in the Location textbox.");
        locatorPage.enterLocation("375 Washington Street, Boston, MA 02108");
        Assert.assertEquals(locatorPage.getSearchInput().getAttribute("value"), "375 Washington Street, Boston, MA 02108");

        test.info("Step 5: Click the 'Search for Stores' button.");
        locatorPage.clickSearchButton();
        Assert.assertTrue(resultsPage.getStoreCards().size() > 0, "Store results are displayed");

        test.info("Step 6: Click the 'Set My Store' button for the Boston location.");
        resultsPage.getSetMyStoreButton(resultsPage.getStoreCardByAddress("375 Washington Street, Boston, MA 02108")).click();
        // No assertion for confirmation, as message/indicator is not specified in steps
    }
}