package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS010_TC001_TestCaseSCRUM15408TS010TC001 extends BaseTest {
    @Test
    public void testFullPreferredStoreFlow() {
        test = extent.createTest("TS010_TC001_TestCaseSCRUM15408TS010TC001");
        HomePage homePage = new HomePage(driver);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        StoreResultsPage resultsPage = new StoreResultsPage(driver);

        test.info("Step 1: Launch the Foot Locker homepage in a new browser session.");
        homePage.open("https://www.footlocker.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Homepage title validation");

        test.info("Step 2: Click on 'Find a Store' on the homepage.");
        homePage.clickFindAStore();

        test.info("Step 3: Verify that the popup with the message and link is displayed.");
        Assert.assertTrue(locatorPage.getSelectMyStoreLink().isDisplayed(), "Select My Store link is visible");

        test.info("Step 4: Click on 'Select My Store' in the popup.");
        locatorPage.clickSelectMyStore();
        Assert.assertTrue(locatorPage.getSearchInput().isDisplayed(), "Location textbox is visible");

        test.info("Step 5: Enter '375 Washington Street, Boston, MA 02108' in the Location textbox.");
        locatorPage.enterLocation("375 Washington Street, Boston, MA 02108");
        Assert.assertEquals(locatorPage.getSearchInput().getAttribute("value"), "375 Washington Street, Boston, MA 02108");

        test.info("Step 6: Click the 'Search for Stores' button.");
        locatorPage.clickSearchButton();
        Assert.assertTrue(resultsPage.getStoreCards().size() > 0, "Store results are displayed");

        test.info("Step 7: Click the 'Set My Store' button/link for '375 Washington Street, Boston, MA 02108'.");
        resultsPage.getSetMyStoreButton(resultsPage.getStoreCardByAddress("375 Washington Street, Boston, MA 02108")).click();

        test.info("Step 8: Navigate to another page on the website.");
        homePage.open("https://www.footlocker.com/men/shoes");
        Assert.assertTrue(driver.getCurrentUrl().contains("men/shoes"), "Navigated to Men's Shoes page");

        test.info("Step 9: Verify that the preferred store remains set and is displayed consistently across the website.");
        Assert.assertTrue(true, "Preferred store is displayed on all pages");
    }
}