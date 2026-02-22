package tests;

import core.BrowserUtils;
import core.ConfigReader;
import core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS001_TC002_TestCaseSCRUM15408TS001TC002 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @Test
    public void testSelectMyStoreShowsLocationAndSearch() {
        BrowserUtils.navigateTo(driver, ConfigReader.get("baseUrl"));
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(resultsPage.isLocationTextboxPresent(), "Location textbox should be present");
        Assert.assertTrue(resultsPage.isSearchButtonPresent(), "Search button should be present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
