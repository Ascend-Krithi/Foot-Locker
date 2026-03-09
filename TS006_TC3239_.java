/**
 * TC_ID: 3239
 * Description: Validate empty results fallback message
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

public class TS006_TC3239_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testEmptyResultsFallback() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("NoSuchPlaceFallback");
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isEmptyResultsDisplayed(), "Fallback empty results message should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
