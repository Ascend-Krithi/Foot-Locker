/**
 * Acceptance Criteria ID: 1646
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, click Find a Store, verify popup message and Select My Store link visibility
 */
package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.StoreLocatorPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPopupTest {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @Before
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testFindAStorePopup() {
        homePage.clickFindAStore();
        String popupMsg = storeLocatorPage.getPopupMessage();
        Assert.assertTrue("Popup message should contain expected text", popupMsg.contains("Choose a preferred store to make shopping easier"));
        Assert.assertTrue("Select My Store link should be visible", storeLocatorPage.isSelectMyStoreLinkVisible());
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
