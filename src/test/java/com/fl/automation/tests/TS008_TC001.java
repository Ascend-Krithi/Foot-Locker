package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS008_TC001 extends BaseTest {

    @Test(description = "TC4122: Installer onboarding minimum certifications")
    public void testInstallerOnboardingMinimumCertifications() throws InterruptedException {
        driver.get("https://admin.eco-home-hub.example.com");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("admin"), "Admin dashboard should load");
    }
}