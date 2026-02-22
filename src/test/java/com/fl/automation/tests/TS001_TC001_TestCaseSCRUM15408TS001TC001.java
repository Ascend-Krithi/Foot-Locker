package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
    /*
     * Acceptance Criteria ID: 1646
     * Test Scenario ID: SCRUM-15408 TS-001
     * Test Case ID: TC-001
     * Description: Launch homepage, click Find a Store, verify popup message and Select My Store link visibility
     */

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));
    }

    @Test
    public void testFindAStorePopupAndLink() {
        HomePage homePage = new HomePage();
        homePage.clickFindAStore();
        StoreLocatorPage storeLocatorPage = new StoreLocatorPage();
        Assert.assertTrue(storeLocatorPage.isPopupMessageDisplayed(), "Popup message should be displayed");
        Assert.assertTrue(storeLocatorPage.isSelectMyStoreLinkVisible(), "Select My Store link should be visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
