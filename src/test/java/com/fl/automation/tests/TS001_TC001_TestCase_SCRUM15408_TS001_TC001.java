package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

/**
 * Acceptance Criteria ID: 
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Test Case - SCRUM-15408 TS-001 TC-001
 */
public class TS001_TC001_TestCase_SCRUM15408_TS001_TC001 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage();
        storeLocatorPage = new StoreLocatorPage();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test(description = "Verify Find a Store popup and Select My Store link")
    public void testFindAStorePopupAndSelectMyStoreLink() {
        // Step 1: Launch the Foot Locker homepage (done in setUp)
        Assert.assertEquals(driver.getCurrentUrl(), ConfigReader.get("base.url"), "Homepage URL mismatch");

        // Step 2: Click on the 'Find a Store' button
        homePage.clickFindAStore();

        // Step 3: Observe the popup message
        Assert.assertTrue(storeLocatorPage.isPopupMessageDisplayed(), "Popup message is not displayed");
        String popupText = storeLocatorPage.getPopupMessageText();
        Assert.assertEquals(popupText, "Choose a preferred store to make shopping easier", "Popup message text mismatch");

        // Step 4: Check for the presence of the 'Select My Store' link within the popup
        Assert.assertTrue(storeLocatorPage.isSelectMyStoreLinkVisible(), "'Select My Store' link is not visible in popup");
    }
}
