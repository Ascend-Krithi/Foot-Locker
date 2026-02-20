package com.fl.automation.tests;

import com.aventstack.extentreports.ExtentTest;
import com.fl.automation.core.BaseTest;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_TestCase_SCRUM_15408_TS_001_TC_001 extends BaseTest {
    @Test(description = "Test Case - SCRUM-15408 TS-001 TC-001")
    public void testFindAStorePopup() {
        test.set(extent.createTest("Test Case - SCRUM-15408 TS-001 TC-001"));
        ExtentTest logger = test.get();
        HomePage homePage = new HomePage();

        // Step 2: Launch homepage
        logger.info("Step 2: Launch the Foot Locker homepage in a browser.");
        String url = ConfigReader.get("baseUrl");
        homePage.open(url);
        Assert.assertTrue(homePage.getFindAStoreLink().isDisplayed(), "Foot Locker homepage loads successfully.");

        // Step 3: Click Find a Store
        logger.info("Step 3: Click on the 'Find a Store' link/button.");
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupDisplayed(), "'Find a Store' popup appears below the link/button.");

        // Step 4: Observe popup content
        logger.info("Step 4: Observe the popup content.");
        Assert.assertTrue(homePage.isPopupMessageDisplayed("Choose a preferred store to make shopping easier"), "Popup displays the message 'Choose a preferred store to make shopping easier'.");
        Assert.assertTrue(homePage.isFindAStorePopupDisplayed(), "A visible 'Select My Store' link is present.");
    }
}
