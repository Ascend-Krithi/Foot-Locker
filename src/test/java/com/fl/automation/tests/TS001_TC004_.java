package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_ extends BaseTest {
    @Test(description = "TC 3196: Launch, Find Store, Select My Store, enter 'Boston, MA', Search, verify store at '375 Washington Street, Boston, MA 02108'")
    public void testBostonStoreAddressPresent() {
        test = extent.createTest("TC 3196: Boston store address present");
        driver.get("https://www.footlocker.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.getStoreLocatorHelper().selectMyStoreLink().click();
        WebElement searchInput = homePage.getStoreLocatorHelper().searchInput();
        searchInput.clear();
        searchInput.sendKeys("Boston, MA");
        homePage.getStoreLocatorHelper().searchButton().click();
        boolean found = false;
        for (WebElement card : homePage.getStoreLocatorHelper().storeResultCards()) {
            WebElement address = homePage.getStoreLocatorHelper().storeAddressInCard(card);
            if (address.getText().contains("375 Washington Street, Boston, MA 02108")) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Store at 375 Washington Street, Boston, MA 02108 should be present");
    }
}
