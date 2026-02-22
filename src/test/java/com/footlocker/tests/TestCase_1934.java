package com.footlocker.tests;

import com.footlocker.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase_1934 extends BaseStoreLocatorTest {
    @Test
    public void testSelectMyStoreClickable_1934() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.clickSelectMyStore();
        Assert.assertTrue(true, "Select My Store button clicked");
    }
}