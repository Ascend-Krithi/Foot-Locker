package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-001: Verify Find a Store and Select My Store link")
    public void testFindStoreAndSelectMyStoreLink() {
        homePage.clickFindStore();
        
        boolean isSelectMyStoreVisible = homePage.isSelectMyStoreVisible();
        Assert.assertTrue(isSelectMyStoreVisible, "Select My Store link should be visible after clicking Find a Store");
    }
}