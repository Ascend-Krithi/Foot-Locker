/**
 * Acceptance Criteria ID: 1646
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 */
package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.StoreLocatorPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testFindAStorePopup() {
        homePage.clickFindAStore();
        String popupMsg = storeLocatorPage.getPopupMessage();
        Assert.assertTrue(popupMsg.contains("Choose a preferred store to make shopping easier"), "Popup message validation");
        Assert.assertTrue(storeLocatorPage.isSelectMyStoreLinkVisible(), "Select My Store link/button should be visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
