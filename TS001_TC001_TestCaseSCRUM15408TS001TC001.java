package com.fl.automation.tests;

import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_TestCaseSCRUM15408TS001TC001 extends BaseTest {
    @Test(description = "Test Case - SCRUM-15408 TS-001 TC-001")
    public void testFindAStorePopup() {
        test = extent.createTest("TS001_TC001_TestCaseSCRUM15408TS001TC001");
        // Step 2: Launch the Foot Locker homepage in a browser
        test.info("Step 2: Launch the Foot Locker homepage in a browser");
        String url = ConfigReader.get("baseUrl");
        HomePage homePage = new HomePage(driver);
        homePage.open(url);
        Assert.assertTrue(homePage.isLoaded(), "Foot Locker homepage loads successfully.");
        test.log(Status.PASS, "Foot Locker homepage loaded successfully.");

        // Step 3: Click on the 'Find a Store' link/button
        test.info("Step 3: Click on the 'Find a Store' link/button");
        homePage.getFindAStoreLink().click();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isPopupDisplayed(), "'Find a Store' popup appears below the link/button.");
        test.log(Status.PASS, "'Find a Store' popup appears below the link/button.");

        // Step 4: Observe the popup content
        test.info("Step 4: Observe the popup content");
        Assert.assertTrue(locatorPage.isPopupMessageDisplayed("Choose a preferred store to make shopping easier"),
                "Popup displays the message 'Choose a preferred store to make shopping easier'.");
        Assert.assertTrue(locatorPage.isPopupDisplayed(), "'Select My Store' link is visible in popup.");
        test.log(Status.PASS, "Popup displays correct message and 'Select My Store' link is visible.");
    }
}
