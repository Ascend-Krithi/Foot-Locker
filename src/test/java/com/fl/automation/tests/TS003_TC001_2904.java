package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;

/**
 * TC_ID: 2904
 * Description: Test Case - SCRUM-17166 TS-003 TC-001
 */
public class TS003_TC001_2904 {
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
        locatorPage.enterSearch("Los Angeles");
        resultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void verifyStoreResultsDisplayed() {
        Assert.assertTrue(resultsPage.getStoreResultCards().size() > 0, "Store results should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
