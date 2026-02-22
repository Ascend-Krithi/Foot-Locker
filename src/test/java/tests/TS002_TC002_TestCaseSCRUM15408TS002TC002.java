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

public class TS002_TC002_TestCaseSCRUM15408TS002TC002 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @Test
    public void testSearchAntarcticaShowsNoStores() {
        BrowserUtils.navigateTo(driver, ConfigReader.get("baseUrl"));
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        locatorPage.enterSearchLocation("Antarctica");
        locatorPage.clickSearchButton();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(resultsPage.isNoStoresFoundMessageDisplayed(), "No stores found message should be displayed for Antarctica");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
