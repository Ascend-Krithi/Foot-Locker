package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test
    public void testStoreLocatorPopupElements() {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookiesIfPresent();

        homePage.clickFindAStore();

        homePage.clickSelectMyStore();

        WebElement searchInput = homePage.getSearchInput();
        Assert.assertNotNull(searchInput, "Location textbox should be present");
        Assert.assertTrue(searchInput.isDisplayed(), "Location textbox should be displayed");
    }
}
