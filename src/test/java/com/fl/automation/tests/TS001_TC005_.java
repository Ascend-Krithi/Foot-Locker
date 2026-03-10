package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS001_TC005_ extends BaseTest {

    @Test
    public void setMyStoreForBoston() {
        HomePage home = new HomePage(driver);
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);

        home.clickFindAStore();

        WebElement selectBtn = helper.waitForFirstVisible(home.getSelectMyStoreLocators());
        helper.clickWithFallback(selectBtn);

        helper.waitForStoreLocatorToBeReady();

        WebElement locationInput = helper.waitForFirstVisible(home.getSearchInputLocators());
        locationInput.clear();
        locationInput.sendKeys("Boston, MA");

        By[] searchBtnCandidates = new By[]{
                By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]"),
                By.cssSelector("button[type='submit']")
        };

        WebElement searchBtn = helper.waitForFirstVisible(searchBtnCandidates);
        helper.clickWithFallback(searchBtn);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> results = driver.findElements(home.getStoreResultCards());
        Assert.assertTrue(results.size() > 0, "No store results found");

        WebElement targetStore = null;
        for (WebElement result : results) {
            WebElement addressEl = result.findElement(home.getStoreAddress());
            String address = addressEl.getText();
            if (address.contains("375 Washington Street") && address.contains("Boston") && address.contains("02108")) {
                targetStore = result;
                break;
            }
        }

        Assert.assertNotNull(targetStore, "Target store not found");

        WebElement setMyStoreBtn = targetStore.findElement(home.getSetMyStoreButton());
        helper.scrollIntoView(setMyStoreBtn);
        helper.clickWithFallback(setMyStoreBtn);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}