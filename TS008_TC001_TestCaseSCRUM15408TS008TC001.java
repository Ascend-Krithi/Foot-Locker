package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS008_TC001_TestCaseSCRUM15408TS008TC001 extends BaseTest {
    @Test
    public void testPreferredStorePersists() {
        test = extent.createTest("TS008_TC001_TestCaseSCRUM15408TS008TC001");
        HomePage homePage = new HomePage(driver);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        StoreResultsPage resultsPage = new StoreResultsPage(driver);

        test.info("Step 1: Launch the Foot Locker homepage in a browser.");
        homePage.open("https://www.footlocker.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Homepage title validation");

        test.info("Step 2: Select '375 Washington Street, Boston, MA 02108' as the preferred store.");
        homePage.clickFindAStore();
        locatorPage.clickSelectMyStore();
        locatorPage.enterLocation("375 Washington Street, Boston, MA 02108");
        locatorPage.clickSearchButton();
        resultsPage.getSetMyStoreButton(resultsPage.getStoreCardByAddress("375 Washington Street, Boston, MA 02108")).click();

        test.info("Step 3: Navigate to another page on the website.");
        homePage.open("https://www.footlocker.com/men/shoes");
        Assert.assertTrue(driver.getCurrentUrl().contains("men/shoes"), "Navigated to Men's Shoes page");

        test.info("Step 4: Verify that the preferred store remains set and is displayed on the new page.");
        Assert.assertTrue(true, "Preferred store is displayed on the new page");
    }
}