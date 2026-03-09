/**
 * TC_ID: 3199
 * Description: Validate Set My Store button is present on store card
 */
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC3199_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStoreButtonPresent() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("Houston");
        StoreResultsPage results = new StoreResultsPage();
        boolean present = false;
        try {
            results.clickSetMyStoreOnFirstCard();
            present = true;
        } catch (Exception e) {
            present = false;
        }
        Assert.assertTrue(present, "Set My Store button should be present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
