package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;

/**
 * TC_ID: 2905
 * Description: Test Case - SCRUM-17166 TS-004 TC-001
 */
public class TS004_TC001_2905 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage locatorPage;
    private StoreResultsPage resultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        homePage.clickFindStoreHeader();
        locatorPage = new StoreLocatorPage(driver);
        locatorPage.enterSearch("Chicago");
        resultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void verifyStoreAddressDisplayed() {
        for (WebElement card : resultsPage.getStoreResultCards()) {
            String address = resultsPage.getStoreAddress(card);
            Assert.assertFalse(address.isEmpty(), "Store address should be displayed");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
