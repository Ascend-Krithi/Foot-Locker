package com.footlocker.tests;

import com.footlocker.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase_1729 extends BaseStoreLocatorTest {
    @Test
    public void testFindStoreHeaderDisplayed_1729() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isFindStoreHeaderDisplayed(), "Find Store header should be displayed");
    }
}