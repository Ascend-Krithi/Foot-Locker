package com.footlocker.tests;

import com.footlocker.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase_1936 extends BaseStoreLocatorTest {
    @Test
    public void testStoreResultDisplayed_1936() {
        driver.get(baseUrl);
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        locatorPage.searchForStore("Chicago");
        Assert.assertTrue(locatorPage.isStoreResultDisplayed(), "Store result should be displayed");
    }
}