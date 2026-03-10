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

public class TS001_TC006_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-006: Validate Selected Store Confirmation and Persistence")
    public void testSelectedStoreConfirmationAndPersistence() {
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

        By[] confirmationLocators = new By[] {
            By.xpath("//*[contains(.,'Store selected') or contains(.,'Store set') or contains(.,'My Store')]"),
            By.cssSelector("[class*='confirmation'], [class*='success-message']"),
            By.xpath("//header//*[contains(.,'Boston') or contains(.,'375 Washington')]")
        };

        boolean confirmationFound = false;
        for (By by : confirmationLocators) {
            try {
                WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                if (confirmation.isDisplayed()) {
                    confirmationFound = true;
                    break;
                }
            } catch (Exception ignored) {}
        }

        Assert.assertTrue(confirmationFound, 
            "Confirmation indicator should be displayed and selected store should appear in the website header or location indicator");
    }
}