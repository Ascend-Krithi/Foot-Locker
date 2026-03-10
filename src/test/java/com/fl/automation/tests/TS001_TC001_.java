package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-001")
    public void testFindStorePopupAndSelectMyStoreLink(){

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), 
            "Homepage should load with 'Foot Locker' in title");

        homePage.clickFindStore();

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), 
            "'Select My Store' link should be visible within the popup");
    }
}
