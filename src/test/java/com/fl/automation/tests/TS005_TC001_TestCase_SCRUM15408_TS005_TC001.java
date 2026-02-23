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
 * Test Case ID: 2017
 * Test Case Name: Test Case - SCRUM-15408 TS-005 TC-001
 * Description: Launch homepage, open Store Locator, enter 'Boston, MA', click Search,
 * click 'Set My Store' for '375 Washington Street, Boston, MA 02108',
 * and verify confirmation indicator is displayed.
 */
public class TS005_TC001_TestCase_SCRUM15408_TS005_TC001 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStoreConfirmationDisplayed() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();
        
        String targetAddress = "375 Washington Street, Boston, MA 02108";
        resultsPage.clickSetMyStoreForAddress(targetAddress);
        
        Assert.assertTrue(resultsPage.isConfirmationDisplayed(), 
            "Confirmation indicator not displayed after setting store");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}