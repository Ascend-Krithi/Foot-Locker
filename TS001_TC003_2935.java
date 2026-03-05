package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-003 (2935)
 * Description: Search for stores in Boston, MA -> Verify store address 375 Washington Street, Boston, Massachusetts, 02108
 */
public class TS001_TC003_2935 {
    private WebDriver driver;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testVerifyStoreAddressBostonMassachusetts() {
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchButton();
        boolean found = false;
        for (WebElement card : storeResultsPage.getStoreResultCards()) {
            String address = storeResultsPage.getStoreAddress(card).getText();
            if (address.contains("375 Washington Street") && address.contains("Boston") && address.contains("Massachusetts") && address.contains("02108")) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Store address 375 Washington Street, Boston, Massachusetts, 02108 should be present in results");
    }
}