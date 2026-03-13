package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS009_TC034 extends BaseTest {

    @Test(description = "TC4149: Edit user profile")
    public void testEditUserProfile() throws InterruptedException {
        driver.get("https://eco-renovation.com/marketplace/profile/edit");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/profile/edit"), "Edit profile page should load");
    }
}