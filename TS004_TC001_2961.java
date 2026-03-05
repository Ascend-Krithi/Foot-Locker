package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
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
 * Test Scenario ID: TS-004
 * Test Case ID: TC-001 (2961)
 * Description: Launch homepage -> Click Find a Store -> Click Select My Store -> Enter Boston, MA -> Click Search -> Verify store address 375 Washington Street, Boston, MA 02108
 */
public class TS004_TC001_2961 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testVerifyStoreAddressBoston() {
        homePage.launchHomePage(ConfigReader.getProperty("baseUrl"));
        homePage.clickFindStoreHeader();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchButton();
        boolean found = false;
        for (WebElement card : storeResultsPage.getStoreResultCards()) {
            String address = storeResultsPage.getStoreAddress(card).getText();
            if (address.contains("375 Washington Street") && address.contains("Boston") && address.contains("02108")) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Store address 375 Washington Street, Boston, MA 02108 should be present in results");
    }
}