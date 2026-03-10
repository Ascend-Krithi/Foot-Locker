package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007_ extends BaseTest {
    @Test(description = "TC 3199: Same as TC-005 plus navigate to another page, return to homepage, verify store persists")
    public void testStorePersistsAfterNavigation() {
        test = extent.createTest("TC 3199: Store persists after navigation");
        driver.get("https://www.footlocker.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.getStoreLocatorHelper().selectMyStoreLink().click();
        WebElement searchInput = homePage.getStoreLocatorHelper().searchInput();
        searchInput.clear();
        searchInput.sendKeys("Boston, MA");
        homePage.getStoreLocatorHelper().searchButton().click();
        boolean setClicked = false;
        for (WebElement card : homePage.getStoreLocatorHelper().storeResultCards()) {
            WebElement address = homePage.getStoreLocatorHelper().storeAddressInCard(card);
            if (address.getText().contains("375 Washington Street, Boston, MA 02108")) {
                WebElement setBtn = homePage.getStoreLocatorHelper().setMyStoreButtonInCard(card);
                try {
                    setBtn.click();
                } catch (Exception e) {
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", setBtn);
                }
                setClicked = true;
                break;
            }
        }
        Assert.assertTrue(setClicked, "Set My Store should be clicked for Boston store");
        // Navigate to another page (e.g., Men)
        driver.get("https://www.footlocker.com/category/mens.html");
        // Return to homepage
        driver.get("https://www.footlocker.com/");
        // Verify store persists in header
        boolean headerHasStore = false;
        try {
            headerHasStore = driver.findElement(org.openqa.selenium.By.xpath("//header//*[contains(.,'375 Washington Street') or contains(.,'Boston')]")).isDisplayed();
        } catch (Exception ignored) {}
        Assert.assertTrue(headerHasStore, "Store should persist in header after navigation");
    }
}
