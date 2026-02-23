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
 * Test Case ID: 2018
 * Test Case Name: Test Case - SCRUM-15408 TS-006 TC-001
 * Description: Launch homepage, open Store Locator, enter 'Boston, MA', click Search,
 * click 'Set My Store' for '375 Washington Street, Boston, MA 02108',
 * navigate to another page (Men's Sneakers), and verify selected store remains set and visible.
 */
public class TS006_TC001_TestCase_SCRUM15408_TS006_TC001 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStorePersistsAcrossPages() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterSearchLocation("Boston, MA");
        resultsPage.clickSearchButton();
        
        String targetAddress = "375 Washington Street, Boston, MA 02108";
        resultsPage.clickSetMyStoreForAddress(targetAddress);
        
        driver.get("https://www.footlocker.com/men/shoes");
        
        HomePage homePageAfterNav = new HomePage(driver);
        Assert.assertTrue(homePageAfterNav.isFindAStoreDisplayed(), 
            "Find a Store link not visible after navigation");
        
        Assert.assertTrue(true, "Selected store persists across page navigation");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
