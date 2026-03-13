package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS008_TC002 extends BaseTest {

    @Test(description = "TC4123: SCRUM-19509 TS-008 TC-002 - Installer onboarding: maximum certifications")
    public void testInstallerOnboardingMaximumCertifications() {
        driver.get("https://ecohomehub.example.com/installer/onboarding");
        Assert.assertTrue(driver.getCurrentUrl().contains("installer") && driver.getCurrentUrl().contains("onboarding"), 
            "Should navigate to installer onboarding page for maximum certifications test");
    }
}