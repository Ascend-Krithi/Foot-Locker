package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TS001_TC003_ extends BaseTest {

    @Test
    public void searchBostonMA() {
        HomePage home = new HomePage(driver);
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);

        home.clickFindAStore();

        WebElement selectBtn = helper.waitForFirstVisible(home.getSelectMyStoreLocators());
        helper.clickWithFallback(selectBtn);

        helper.waitForStoreLocatorToBeReady();

        WebElement locationInput = helper.waitForFirstVisible(home.getSearchInputLocators());
        Assert.assertNotNull(locationInput, "Location input not found");
        locationInput.clear();
        locationInput.sendKeys("Boston, MA");

        By[] searchBtnCandidates = new By[]{
                By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]"),
                By.cssSelector("button[type='submit']"),
                By.cssSelector("button[aria-label*='Search' i]")
        };

        WebElement searchBtn = helper.waitForFirstVisible(searchBtnCandidates);
        Assert.assertNotNull(searchBtn, "Search button not found");
        helper.clickWithFallback(searchBtn);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> results = driver.findElements(home.getStoreResultCards());
        Assert.assertTrue(results.size() > 0, "No store results found for Boston, MA");
    }
}