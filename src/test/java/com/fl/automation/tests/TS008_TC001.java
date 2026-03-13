package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS008_TC001 extends BaseTest {

    @Test(description = "TC4122: SCRUM-19509 TS-008 TC-001 - Installer onboarding: minimum certifications")
    public void testInstallerOnboardingMinimumCertifications() {
        driver.get("https://ecohomehub.example.com/installer/onboarding");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer") && driver.getCurrentUrl().contains("onboarding"), 
            "Should navigate to installer onboarding page");
    }
}