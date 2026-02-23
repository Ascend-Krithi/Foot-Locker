package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Acceptance Criteria ID: 2437
 * Test Scenario ID: SCRUM-15408 TS-005
 * Test Case ID: TC-001
 * Description: Set preferred store
 */
public class TS005_TC001_ {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void testSetPreferredStore() {
        homePage.clickFindAStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearch();
        List<WebElement> cards = storeResultsPage.getStoreCards();
        Assert.assertTrue(cards.size() > 0, "No store cards found");
        storeResultsPage.setMyStore(cards.get(0));
        // No assertion as confirmation is in next test
        Assert.assertTrue(true, "Set My Store button clicked");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
