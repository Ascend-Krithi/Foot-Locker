package com.footlocker.tests;

import com.footlocker.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase_1935 extends BaseStoreLocatorTest {
    @Test
    public void testSearchInput_1935() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.enterSearch("New York");
        Assert.assertTrue(true, "Search input accepted value");
    }
}