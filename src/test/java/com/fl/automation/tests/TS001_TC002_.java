package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-002")
    public void testStoreLocatorPopupElements(){

        HomePage homePage = new HomePage(driver);

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

        Assert.assertTrue(homePage.isLocationTextboxVisible(), 
            "'Location' textbox should be visible in store locator popup");

        Assert.assertTrue(homePage.isSearchButtonVisible(), 
            "'Search for Stores' button should be visible in store locator popup");
    }
}
