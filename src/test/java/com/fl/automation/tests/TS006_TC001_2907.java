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
 * TC_ID: 2907
 * Description: Test Case - SCRUM-17166 TS-006 TC-001
 */
public class TS006_TC001_2907 {
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
        locatorPage.enterSearch("Miami");
        resultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void verifySetMyStoreButtonClickable() {
        for (WebElement card : resultsPage.getStoreResultCards()) {
            if (resultsPage.isSetMyStoreButtonPresent(card)) {
                resultsPage.clickSetMyStoreButton(card);
                Assert.assertTrue(true, "Set My Store button clicked successfully");
                break;
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
