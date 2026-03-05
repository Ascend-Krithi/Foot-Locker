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
Acceptance Criteria ID: 3193
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-001
Description: Launch app, click Find a Store, verify Select My Store link
*/
public class TS001_TC001_ {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testFindStoreLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        try {
            locatorPage.clickSelectMyStore();
            Assert.assertTrue(true, "Select My Store link/button is present");
        } catch (Exception e) {
            Assert.fail("Select My Store link/button not found");
        }
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}