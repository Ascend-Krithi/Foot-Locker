package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC027 extends BaseTest {

    @Test(description = "TC4142: Project details page verification")
    public void testProjectDetailsPage() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/project/12345");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/project/"), "Project details page should load");
    }
}