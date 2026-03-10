package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS001_TC003_ extends BaseTest {

    @Test
    public void testSearchStoresByLocation() {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookiesIfPresent();

        homePage.clickFindAStore();

        homePage.clickSelectMyStore();

        homePage.enterSearchLocation("Boston, MA");

        homePage.clickSearchButton();

        List<WebElement> storeResults = homePage.getStoreResults();
        Assert.assertTrue(storeResults.size() > 0, "Search results should display stores near Boston, MA");
    }
}
