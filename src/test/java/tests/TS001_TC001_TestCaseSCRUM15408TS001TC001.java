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
import pages.StoreResultsPage;

public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @Test
    public void testFindAStorePopup() {
        BrowserUtils.navigateTo(driver, ConfigReader.get("baseUrl"));
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(resultsPage.isPopupDisplayed(), "Popup should be displayed");
        Assert.assertTrue(resultsPage.isSelectMyStoreLinkPresent(), "Select My Store link should be present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
