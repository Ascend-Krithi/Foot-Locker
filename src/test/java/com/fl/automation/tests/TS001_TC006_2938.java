package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test Case ID: 2938
 * Test Case Name: Test Case - SCRUM-17166 TS-001 TC-006
 * Description: Set the store at '375 Washington Street, Boston, Massachusetts, 02108' as 'My Store' and confirm selection, navigate to another page on the website (e.g., Men's Sneakers), and verify selected store remains set as the preferred store and confirmation indicator is still visible.
 */
public class TS001_TC006_2938 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreSelectionPersistsAfterNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();

        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.clickSelectMyStore();

        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.enterLocation("Boston, MA");
        resultsPage.clickSearchButton();

        resultsPage.clickSetMyStoreForAddress("375 Washington Street, Boston, Massachusetts, 02108");

        driver.navigate().to(ConfigReader.get("base.url") + "/category/mens/shoes/sneakers.html");

        Assert.assertTrue(resultsPage.isStoreSetConfirmationDisplayed(), "Selected store not persisted after navigation to Men's Sneakers");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}