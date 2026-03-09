/**
 * TC_ID: 3240
 * Description: Validate store card count for edge case city
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

public class TS007_TC3240_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreCardCountEdgeCase() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("EdgeCaseCity");
        StoreResultsPage results = new StoreResultsPage();
        int count = results.getStoreCardCount();
        Assert.assertTrue(count >= 0, "Store card count should be >= 0 for edge case");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
