package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.InstallerSearchPage;

public class TS002_TC003_EcoHomeHub extends BaseTest {
    
    @Test
    public void testInstallerSearch() {
        driver.get("https://ecohomehub.example.com/installers");
        InstallerSearchPage searchPage = new InstallerSearchPage(driver);
        searchPage.searchInstallers("Boston, MA");
        
        Assert.assertTrue(searchPage.areResultsDisplayed(), "Installer search results should be displayed");
    }
}