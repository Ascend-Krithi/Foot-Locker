package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {
    @Test(description = "TC 3194: Launch, click Find a Store, click Select My Store, verify Location textbox and Search button")
    public void testSelectMyStoreShowsSearch() {
        test = extent.createTest("TC 3194: Select My Store - Location textbox and Search button");
        driver.get("https://www.footlocker.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.getStoreLocatorHelper().selectMyStoreLink().click();
        WebElement searchInput = homePage.getStoreLocatorHelper().searchInput();
        WebElement searchBtn = homePage.getStoreLocatorHelper().searchButton();
        Assert.assertTrue(searchInput.isDisplayed(), "Location textbox should be visible");
        Assert.assertTrue(searchBtn.isDisplayed(), "Search button should be visible");
    }
}
