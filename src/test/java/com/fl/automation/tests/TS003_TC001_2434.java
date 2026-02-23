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
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-003
 * Test Case ID: TC-001 (2434)
 * Description: Search for stores in Boston, MA
 */
public class TS003_TC001_2434 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;
    private StoreResultsPage storeResultsPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
        storeResultsPage = new StoreResultsPage(driver);
    }

    @Test
    public void searchStoresInBoston() {
        homePage.clickFindStore();
        storeLocatorPage.enterLocation("Boston, MA");
        storeLocatorPage.clickSearch();
        List<WebElement> stores = storeResultsPage.getStoreCards();
        Assert.assertTrue(stores.size() > 0, "Should display store results for Boston, MA");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
