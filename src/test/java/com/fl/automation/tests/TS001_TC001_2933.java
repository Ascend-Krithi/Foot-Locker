package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;

/**
 * TC_ID: 2933
 * Description: Test Case - SCRUM-17166 TS-001 TC-001
 */
public class TS001_TC001_2933 {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
    }

    @Test
    public void verifyFindStoreHeaderDisplayed() {
        Assert.assertTrue(homePage.isFindStoreHeaderDisplayed(), "Find Store header should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
