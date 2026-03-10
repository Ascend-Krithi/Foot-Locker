package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_ extends BaseTest {

    @Test
    public void testScenario() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isSelectMyStoreVisible(), "Popup did not appear");

        homePage.clickSelectMyStore();

        Assert.assertTrue(homePage.isSearchInputVisible(), "Store locator popup did not open");

        homePage.enterLocation("Boston, MA");

        homePage.clickSearchButton();

        Assert.assertTrue(homePage.areStoreResultsDisplayed(), "Search results are not displayed");

        WebElement storeCard = homePage.findStoreByAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertNotNull(storeCard, "Store with address '375 Washington Street, Boston, MA 02108' not found");
    }
}