package com.fl.automation.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: HAP-21
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001 (2463)
 * Description: Mobile app test - out of scope for web automation
 */
public class TS001_TC001_2463 {
    @Test
    public void skipMobileAppTest() {
        throw new SkipException("Mobile app test - out of scope for web automation");
    }
}
