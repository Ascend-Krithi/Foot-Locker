package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;

public class TS004_TC002_SCRUM19509 extends BaseTest {
    @Test
    public void testDataEncryptionInTransit() {
        driver.get("https://eco-home-hub.example.com");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.startsWith("https://"), "Data should be transmitted over secure protocols (HTTPS)");
    }
}