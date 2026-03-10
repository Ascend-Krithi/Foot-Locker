package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_ extends BaseTest {
    @Test
    public void testScenario() {
        HomePage home = new HomePage(driver);
        // Step 1: Verify homepage loads
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("foot locker"), "Homepage title should contain 'Foot Locker'");
        // Step 2: Click 'Find a Store', verify popup appears
        home.clickFindStore();
        // Step 3: Click 'Select My Store', verify store locator popup opens
        home.clickSelectMyStore();
        // Step 4: Enter 'Boston, MA' in Location textbox
        home.enterLocation("Boston, MA");
        // Step 5: Click 'Search for Stores', verify search results displayed
        home.clickSearchButton();
        Assert.assertTrue(home.areStoreResultsDisplayed(), "Search results should be displayed for Boston, MA");
        // Step 6: Locate store with address '375 Washington Street, Boston, MA 02108'
        WebElement storeCard = home.findStoreByAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertNotNull(storeCard, "Store with address '375 Washington Street, Boston, MA 02108' should be present in results");
        // Step 7: Click 'Set My Store' for Boston location, verify store saved
        home.clickSetMyStoreForCard(storeCard);
        // Step 8: Check for confirmation indicator and verify selected store appears in header/location indicator
        Assert.assertTrue(home.isStoreSetInHeader("Boston"), "Selected store should appear in header/location indicator");
    }
}
