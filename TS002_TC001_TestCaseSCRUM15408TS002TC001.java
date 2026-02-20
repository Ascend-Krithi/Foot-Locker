package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001_TestCaseSCRUM15408TS002TC001 extends BaseTest {
    @Test(description = "Test Case - SCRUM-15408 TS-002 TC-001")
    public void testFindAStoreSelectMyStore() {
        test = extent.createTest("TS002_TC001_TestCaseSCRUM15408TS002TC001");
        // Step 1: Launch the Foot Locker homepage in a browser
        test.info("Step 1: Launch the Foot Locker homepage in a browser");
        String url = ConfigReader.get("baseUrl");
        HomePage homePage = new HomePage(driver);
        homePage.open(url);
        Assert.assertTrue(homePage.isLoaded(), "Foot Locker homepage loads successfully.");
        test.log(Status.PASS, "Foot Locker homepage loaded successfully.");

        // Step 2: Click on the 'Find a Store' link/button
        test.info("Step 2: Click on the 'Find a Store' link/button");
        homePage.getFindAStoreLink().click();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isPopupDisplayed(), "'Find a Store' popup appears.");
        test.log(Status.PASS, "'Find a Store' popup appears.");

        // Step 3: Click on the 'Select My Store' link in the popup
        test.info("Step 3: Click on the 'Select My Store' link in the popup");
        locatorPage.getSelectMyStoreLink().click();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        Assert.assertNotNull(resultsPage.getSearchInput(), "'Location' textbox is present.");
        test.log(Status.PASS, "'Location' textbox is present.");
    }
}
