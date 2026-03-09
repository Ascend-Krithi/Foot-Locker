/**
 * TC_ID: 3245
 * Description: Validate search with long string returns empty results
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

public class TS012_TC3245_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSearchWithLongString() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("ThisIsAVeryLongStringThatShouldNotMatchAnyStoreLocation");
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isEmptyResultsDisplayed(), "Empty results should be displayed for long string search");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
