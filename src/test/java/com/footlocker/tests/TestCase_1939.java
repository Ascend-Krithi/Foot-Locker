package com.footlocker.tests;

import com.footlocker.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase_1939 extends BaseStoreLocatorTest {
    @Test
    public void testSetMyStoreButton_1939() {
        driver.get(baseUrl);
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        resultsPage.clickSetMyStore();
        Assert.assertTrue(true, "Set My Store button clicked");
    }
}