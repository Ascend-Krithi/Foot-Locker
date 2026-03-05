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
Acceptance Criteria ID: 3195
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-003
Description: Launch, Find Store, Select My Store, enter Boston MA, click Search, verify results
*/
public class TS001_TC003_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testSearchStoreResults() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston MA");
        resultsPage.clickSearchButton();
        Assert.assertTrue(resultsPage.isStoreResultsDisplayed(), "Store results not displayed for Boston MA");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}