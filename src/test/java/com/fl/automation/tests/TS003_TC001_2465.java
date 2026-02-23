package com.fl.automation.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: HAP-21
 * Test Scenario ID: TS-003
 * Test Case ID: TC-001 (2465)
 * Description: Mobile app test - out of scope for web automation
 */
public class TS003_TC001_2465 {
    @Test
    public void skipMobileAppTest() {
        throw new SkipException("Mobile app test - out of scope for web automation");
    }
}
