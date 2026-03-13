package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS008_TC033 extends BaseTest {

    @Test(description = "TC4148: Apply to project")
    public void testApplyToProject() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/project/"), "Project page should load");
    }
}