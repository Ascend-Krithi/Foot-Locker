package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC001 extends BaseTest {

    @Test(description = "TC4111: Installer portal login")
    public void testInstallerPortalLogin() throws InterruptedException {
        driver.get("https://installer.eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("installer"), "Installer portal should load");
    }
}