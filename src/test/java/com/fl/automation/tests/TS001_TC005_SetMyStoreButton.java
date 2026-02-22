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
Acceptance Criteria ID: 1937
Test Scenario ID: SCRUM-15408 TS-001
Test Case ID: TC-005
Description: Verify 'Set My Store' button is present inside store result card and clickable.
*/
public class TS001_TC005_SetMyStoreButton {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testSetMyStoreButton() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.enterSearch("New York");
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        try {
            resultsPage.setFirstStoreAsMyStore();
            Assert.assertTrue(true, "Set My Store button is clickable");
        } catch (Exception e) {
            Assert.fail("Set My Store button not found or not clickable");
        }
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}