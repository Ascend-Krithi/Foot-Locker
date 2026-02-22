package tests;

import factory.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import utils.ConfigReader;

/**
 * Acceptance Criteria ID: 1646
 * Test Scenario ID: SCRUM-15408 TS-001
 * Test Case ID: TC-001
 * Description: Launch homepage, click Find a Store, verify popup message and Select My Store link visibility
 */
public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test(description = "Launch homepage, click Find a Store, verify popup message and Select My Store link visibility")
    public void testFindAStorePopup() {
        String baseUrl = ConfigReader.getProperty("base.url");
        DriverFactory.getDriver().get(baseUrl);

        HomePage homePage = new HomePage();
        homePage.clickFindAStore();

        StoreLocatorPage storeLocatorPage = new StoreLocatorPage();
        String popupMsg = storeLocatorPage.getPopupMessage();
        Assert.assertTrue(popupMsg.contains("Choose a preferred store to make shopping easier"),
                "Popup message should contain expected text.");

        Assert.assertTrue(storeLocatorPage.isSelectMyStoreLinkVisible(),
                "'Select My Store' link should be visible in the popup.");
    }
}
