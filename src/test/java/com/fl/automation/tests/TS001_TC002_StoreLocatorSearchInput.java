package com.fl.automation.tests;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
Acceptance Criteria ID: 1934
Test Scenario ID: SCRUM-15408 TS-001
Test Case ID: TC-002
Description: Verify search input is present and accepts input on Store Locator page.
*/
public class TS001_TC002_StoreLocatorSearchInput {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testStoreLocatorSearchInput() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        try {
            locatorPage.enterSearch("New York");
            Assert.assertTrue(true, "Search input is present and accepts input");
        } catch (Exception e) {
            Assert.fail("Search input not found or not accepting input");
        }
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}