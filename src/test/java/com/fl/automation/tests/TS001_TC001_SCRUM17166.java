package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;

public class TS001_TC001_SCRUM17166 extends BaseTest {
    @Test
    public void testFindAStoreLink() {
        driver.get("https://www.footlocker.com");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "Select My Store link should be visible");
    }
}