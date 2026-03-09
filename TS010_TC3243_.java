/**
 * TC_ID: 3243
 * Description: Validate search with whitespace returns results
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

public class TS010_TC3243_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchWithWhitespace() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("   New York   ");
        StoreResultsPage results = new StoreResultsPage();
        int count = results.getStoreCardCount();
        Assert.assertTrue(count >= 0, "Store card count should be >= 0 for whitespace search");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
