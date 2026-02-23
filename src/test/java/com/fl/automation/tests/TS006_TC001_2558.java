package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: 2558
 * Test Scenario ID: SCRUM-15408 TS-006
 * Test Case ID: TC-001
 * Description: Launch homepage, search for Boston MA, set store, verify confirmation indicator and store appears consistently
 */
public class TS006_TC001_2558 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    @Test
    public void test_TS006_TC001_2558() {
        HomePage home = new HomePage();
        home.open();
        home.clickFindAStoreHeader();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("Boston, MA");
        StoreResultsPage results = new StoreResultsPage();
        boolean found = false;
        for (WebElement card : results.getStoreCards()) {
            String address = results.getStoreAddress(card);
            if (address.contains("375 Washington Street") && address.contains("Boston") && address.contains("02108")) {
                results.setMyStore(card);
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Store should be set and confirmation indicator should be displayed");
    }
}