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

/**
 * TC_ID: 2936
 * Description: Test Case - SCRUM-17166 TS-001 TC-004
 */
public class TS001_TC004_2936 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage locatorPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        homePage.clickFindStoreHeader();
        locatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void verifySearchInputAcceptsInput() {
        String searchText = "San Francisco";
        locatorPage.enterSearch(searchText);
        Assert.assertEquals(locatorPage.getSearchInput().getAttribute("value"), searchText, "Search input should accept entered text");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
