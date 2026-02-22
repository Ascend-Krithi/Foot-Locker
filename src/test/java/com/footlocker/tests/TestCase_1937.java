package com.footlocker.tests;

import com.footlocker.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase_1937 extends BaseStoreLocatorTest {
    @Test
    public void testStoreResultCardDisplayed_1937() {
        driver.get(baseUrl);
        StoreResultsPage resultsPage = new StoreResultsPage(driver);
        Assert.assertTrue(resultsPage.isStoreResultCardDisplayed(), "Store result card should be displayed");
    }
}