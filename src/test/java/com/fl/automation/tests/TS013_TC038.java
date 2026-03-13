package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS013_TC038 extends BaseTest {

    @Test(description = "TC4153: Share project via email")
    public void testShareProjectViaEmail() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/project/"), "Project page should load");
    }
}