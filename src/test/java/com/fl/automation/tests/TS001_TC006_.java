package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-006
 * Description: Set Boston store as preferred, verify confirmation and header indicator
 */
public class TS001_TC006_ {
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

    @Test
    public void testSetBostonStoreConfirmationAndHeader() {
        homePage.open(ConfigReader.get("baseUrl"));
        homePage.clickFindAStore();
        storeLocatorPage.clickSelectMyStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearchButton();
        Assert.assertTrue(storeResultsPage.setMyStoreForAddress("375 Washington Street, Boston, MA 02108"), "Set My Store button not found for Boston");
        Assert.assertTrue(storeResultsPage.isConfirmationIndicatorDisplayed(), "Confirmation indicator not displayed");
        Assert.assertTrue(storeResultsPage.isStoreInHeader("375 Washington Street, Boston, MA 02108"), "Selected store not shown in header");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
