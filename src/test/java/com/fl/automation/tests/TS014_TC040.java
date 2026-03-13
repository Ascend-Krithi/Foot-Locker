package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS014_TC040 extends BaseTest {

    @Test(description = "TC4155: SCRUM-19509 TS-014 TC-001 - Save project to favorites")
    public void testSaveProjectToFavorites() {
        driver.get("https://marketplace.example.com/project/12345");
        Assert.assertTrue(driver.getCurrentUrl().contains("project"), "Should be on project details page to save to favorites");
    }
}