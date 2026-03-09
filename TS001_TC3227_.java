/**
 * TC_ID: 3227
 * Description: Validate empty results message when no stores found
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

public class TS001_TC3227_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testEmptyResultsMessage() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("NowherevilleXYZ");
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isEmptyResultsDisplayed(), "Empty results message should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
