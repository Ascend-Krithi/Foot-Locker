package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class TC3193_FindAStorePopup extends BaseTest {
    @Test
    public void testFindAStorePopup() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        Assert.assertTrue(home.isFindAStorePopupDisplayed(), "Find a Store popup should be displayed");
        Assert.assertTrue(home.isSelectMyStoreLinkPresent(), "Select My Store link should be present");
    }
}
