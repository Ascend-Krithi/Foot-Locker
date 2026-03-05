package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-17166
 * Test Scenario ID: TS-001
 * Test Case ID: TC-002 (2901)
 * Description: Launch homepage -> Click Find a Store -> Click Select My Store -> Verify Location textbox and Search button
 */
public class TS001_TC002_2901 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void testSelectMyStoreAndVerifyLocationTextboxAndSearchButton() {
        homePage.launchHomePage(ConfigReader.getProperty("baseUrl"));
        homePage.clickFindStoreHeader();
        storeLocatorPage.clickSelectMyStore();
        Assert.assertTrue(storeLocatorPage.getSearchInput().isDisplayed(), "Location textbox should be displayed");
        Assert.assertTrue(storeLocatorPage.getSearchInput().findElement(org.openqa.selenium.By.xpath("../following-sibling::button[contains(.,'Search') or @type='submit']")).isDisplayed(), "Search button should be displayed");
    }
}