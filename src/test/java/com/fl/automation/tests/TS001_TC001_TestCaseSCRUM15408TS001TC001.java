package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.BrowserUtils;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;

public class TS001_TC001_TestCaseSCRUM15408TS001TC001 {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeMethod
    public void setUp() {
        ConfigReader.initProperties("src/test/resources/config.properties");
        String browser = ConfigReader.getProperty("browser");
        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
        BrowserUtils.maximize(driver);
        BrowserUtils.navigateTo(driver, ConfigReader.getProperty("url"));
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testStoreLocatorSearch() {
        homePage.clickStoreLocator();
        storeLocatorPage.searchStore("New York");
        Assert.assertTrue(storeLocatorPage.isResultDisplayed(), "Store results not displayed!");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
