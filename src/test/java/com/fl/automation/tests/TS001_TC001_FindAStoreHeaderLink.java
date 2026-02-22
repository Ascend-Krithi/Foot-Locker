package com.fl.automation.tests;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
Acceptance Criteria ID: 1729
Test Scenario ID: SCRUM-15408 TS-001
Test Case ID: TC-001
Description: Verify 'Find a Store' header link is present and clickable.
*/
public class TS001_TC001_FindAStoreHeaderLink {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }
    @Test
    public void testFindAStoreHeaderLink() {
        HomePage homePage = new HomePage(driver);
        try {
            homePage.clickFindAStore();
            Assert.assertTrue(true, "Find a Store header link is clickable");
        } catch (Exception e) {
            Assert.fail("Find a Store header link not found or not clickable");
        }
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}