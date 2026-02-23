package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: TC 2433
 * Test Scenario ID: SCRUM-15408 TS-002
 * Test Case ID: TC-001
 * Description: Launch homepage, Click 'Find a Store', Click 'Select My Store' link, Verify popup contains 'Location' textbox and 'Search for Stores' button
 */
public class TS002_TC001_ {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        homePage = new HomePage();
    }

    @Test
    public void testSelectMyStorePopup() {
        homePage.clickFindAStore();
        driver.findElement(By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")).click();
        boolean locationBox = driver.findElements(By.cssSelector("input[type='search']")).size() > 0 ||
                driver.findElements(By.cssSelector("input[name='q']")).size() > 0 ||
                driver.findElements(By.cssSelector("input[aria-label*='Search']")).size() > 0 ||
                driver.findElements(By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]" )).size() > 0;
        Assert.assertTrue(locationBox, "Location textbox not present");
        boolean searchBtn = driver.getPageSource().contains("Search for Stores");
        Assert.assertTrue(searchBtn, "'Search for Stores' button not present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
