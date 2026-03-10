package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TS001_TC003_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003")
    public void testStoreSearchForBoston(){

        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), 
            "Homepage should load");

        homePage.clickFindStore();

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        homePage.clickSelectMyStore();

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        homePage.enterLocation("Boston, MA");

        homePage.clickSearchButton();

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        List<WebElement> storeCards = storeHelper.getStoreCards();

        Assert.assertTrue(storeCards.size() > 0, 
            "Search results should display stores in or near Boston");

        String firstStoreAddress = storeHelper.getStoreAddress(storeCards.get(0));

        Assert.assertTrue(firstStoreAddress.toLowerCase().contains("boston") || 
                         firstStoreAddress.toLowerCase().contains("ma"), 
            "Store address should contain 'Boston' or 'MA'");
    }
}
