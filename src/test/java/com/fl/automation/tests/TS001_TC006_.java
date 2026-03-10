package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_ extends BaseTest {
    @Test(description = "TC 3198: Same as TC-005 plus verify confirmation indicator and store appears in header")
    public void testSetMyStoreConfirmation() {
        test = extent.createTest("TC 3198: Set My Store confirmation and header");
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
        // Confirmation indicator
        boolean confirmed = false;
        for (WebElement card : homePage.getStoreLocatorHelper().storeResultCards()) {
            WebElement address = homePage.getStoreLocatorHelper().storeAddressInCard(card);
            if (address.getText().contains("375 Washington Street, Boston, MA 02108") && card.getText().toLowerCase().contains("my store")) {
                confirmed = true;
                break;
            }
        }
        Assert.assertTrue(confirmed, "Confirmation indicator should appear for Boston store");
        // Store appears in header
        boolean headerHasStore = false;
        try {
            headerHasStore = driver.findElement(org.openqa.selenium.By.xpath("//header//*[contains(.,'375 Washington Street') or contains(.,'Boston')]")).isDisplayed();
        } catch (Exception ignored) {}
        Assert.assertTrue(headerHasStore, "Store should appear in header");
    }
}
