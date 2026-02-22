package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC014_StoreFavoriteFunctionalityTest {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testStoreFavoriteFunctionality() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickStoreLocator();
        StoreLocatorPage locatorPage = new StoreLocatorPage();
        locatorPage.searchByZip("10001");
        StoreResultsPage resultsPage = new StoreResultsPage();
        WebElement firstResult = resultsPage.getResults().get(0);
        // Simulate favorite click (pseudo-code, as actual implementation depends on UI)
        firstResult.click(); // Suppose clicking marks as favorite
        Assert.assertTrue(firstResult.getAttribute("class").contains("favorite"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
