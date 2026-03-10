package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC007_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-007: Validate Selected Store Persists Across Page Navigation")
    public void testSelectedStorePersistsAcrossNavigation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        homePage.clickFindAStore();
        homePage.clickSelectMyStore();

        storeHelper.enterLocation("Boston, MA");
        storeHelper.clickSearchForStores();

        Assert.assertTrue(storeHelper.isStoreResultsDisplayed(), 
            "Search results should be displayed");

        storeHelper.clickSetMyStoreForAddress("375 Washington Street");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        By[] productPageLocators = new By[] {
            By.xpath("//a[contains(@href,'/product') or contains(@href,'/category')]"),
            By.cssSelector("a[href*='product'], a[href*='category']"),
            By.xpath("//nav//a[contains(.,'Men') or contains(.,'Women') or contains(.,'Kids')]")
        };

        WebElement productLink = null;
        for (By by : productPageLocators) {
            try {
                productLink = wait.until(ExpectedConditions.elementToBeClickable(by));
                break;
            } catch (Exception ignored) {}
        }

        if (productLink != null) {
            productLink.click();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.navigate().back();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        By[] storeIndicatorLocators = new By[] {
            By.xpath("//header//*[contains(.,'Boston') or contains(.,'375 Washington')]"),
            By.xpath("//*[contains(.,'My Store') and (contains(.,'Boston') or contains(.,'375 Washington'))]"),
            By.cssSelector("[class*='store-indicator'], [class*='selected-store']")
        };

        boolean storeStillSet = false;
        for (By by : storeIndicatorLocators) {
            try {
                WebElement indicator = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                if (indicator.isDisplayed()) {
                    storeStillSet = true;
                    break;
                }
            } catch (Exception ignored) {}
        }

        Assert.assertTrue(storeStillSet, 
            "Selected store (375 Washington Street, Boston, MA 02108) should persist as preferred store across navigation");
    }
}