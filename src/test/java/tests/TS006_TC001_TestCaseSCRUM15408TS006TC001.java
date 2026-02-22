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

public class TS006_TC001_TestCaseSCRUM15408TS006TC001 {
    private WebDriver driver;
    private static final String STORE_ADDRESS = "375 Washington Street, Boston, MA 02108";

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @Test
    public void testStorePersistsAfterNavigation() {
        BrowserUtils.navigateTo(driver, ConfigReader.get("baseUrl"));
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();
        locatorPage.enterSearchLocation("Boston, MA");
        locatorPage.clickSearchButton();
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.setMyStoreByAddress(STORE_ADDRESS);
        Assert.assertTrue(resultsPage.isConfirmationIndicatorDisplayed(), "Confirmation indicator should be displayed after setting store");
        // Navigate to another page (e.g., homepage)
        BrowserUtils.navigateTo(driver, ConfigReader.get("baseUrl"));
        // Go back to store locator
        homePage.clickFindAStore();
        Assert.assertTrue(resultsPage.isConfirmationIndicatorDisplayed(), "Store selection should persist after navigation");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
