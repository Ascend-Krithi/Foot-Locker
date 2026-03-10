package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test
    public void testFindStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookiesIfPresent();

        homePage.clickFindAStore();

        boolean isVisible = homePage.isSelectMyStoreVisible();
        Assert.assertTrue(isVisible, "'Select My Store' link should be visible in the popup");
    }
}
