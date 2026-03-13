package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC031 extends BaseTest {

    @Test(description = "TC4146: User registration")
    public void testUserRegistration() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/register");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/register"), "Registration page should load");
    }
}