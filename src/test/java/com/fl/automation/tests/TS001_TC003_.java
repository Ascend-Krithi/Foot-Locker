package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {
    @Test(description = "TC 3195: Launch, Find Store, Select My Store, enter 'Boston, MA', click Search, verify results")
    public void testSearchBostonShowsResults() {
        test = extent.createTest("TC 3195: Search Boston, MA shows results");
        driver.get("https://www.footlocker.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.getStoreLocatorHelper().selectMyStoreLink().click();
        WebElement searchInput = homePage.getStoreLocatorHelper().searchInput();
        searchInput.clear();
        searchInput.sendKeys("Boston, MA");
        homePage.getStoreLocatorHelper().searchButton().click();
        boolean hasResults = false;
        try {
            hasResults = !homePage.getStoreLocatorHelper().storeResultCards().isEmpty();
        } catch (Exception ignored) {}
        Assert.assertTrue(hasResults, "Should display store results for Boston, MA");
    }
}
