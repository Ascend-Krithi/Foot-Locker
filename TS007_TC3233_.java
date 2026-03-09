/**
 * TC_ID: 3233
 * Description: Validate store card count is zero for invalid search
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

public class TS007_TC3233_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreCardCountZeroForInvalidSearch() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("InvalidCityXYZ");
        StoreResultsPage results = new StoreResultsPage();
        int count = results.getStoreCardCount();
        Assert.assertEquals(count, 0, "No store cards should be displayed for invalid search");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
