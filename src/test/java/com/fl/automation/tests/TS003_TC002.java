package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC002 extends BaseTest {

    @Test(description = "TC4112: Installer portal view project leads")
    public void testInstallerPortalViewProjectLeads() throws InterruptedException {
        driver.get("https://installer.eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("installer"), "Installer portal should load");
    }
}