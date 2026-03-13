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

public class TS001_TC001 extends BaseTest {

    @Test(description = "TC3193: Launch footlocker.com, click Find a Store, verify popup and Select My Store link")
    public void testFindAStorePopupAndSelectMyStoreLink() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        homePage.navigateToHomePage(BASE_URL);
        Thread.sleep(2000);
        homePage.acceptCookies();
        homePage.closeModalIfPresent();

        WebElement findStoreLink = storeHelper.findStoreLink();
        Assert.assertNotNull(findStoreLink, "Find a Store link should be present");
        storeHelper.clickWithJsFallback(findStoreLink);
        Thread.sleep(2000);

        By popupMessageLocator = By.xpath("//*[contains(text(),'Choose a preferred store')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(popupMessageLocator));
        WebElement popupMessage = driver.findElement(popupMessageLocator);
        Assert.assertTrue(popupMessage.isDisplayed(), "Popup with 'Choose a preferred store' message should be displayed");

        WebElement selectMyStoreLink = storeHelper.findSelectMyStoreLink();
        Assert.assertNotNull(selectMyStoreLink, "Select My Store link should be present");
        Assert.assertTrue(selectMyStoreLink.isDisplayed(), "Select My Store link should be visible");
    }
}
