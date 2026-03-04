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
 * TC_ID: 2906
 * Description: Test Case - SCRUM-17166 TS-005 TC-001
 */
public class TS005_TC001_2906 {
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
        locatorPage.enterSearch("Houston");
        resultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void verifySetMyStoreButtonPresent() {
        for (WebElement card : resultsPage.getStoreResultCards()) {
            Assert.assertTrue(resultsPage.isSetMyStoreButtonPresent(card), "Set My Store button should be present in each card");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
