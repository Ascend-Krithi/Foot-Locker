/**
 * TC_ID: 3238
 * Description: Validate Set My Store button fallback works
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

public class TS005_TC3238_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStoreButtonFallback() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("FallbackStore");
        StoreResultsPage results = new StoreResultsPage();
        try {
            results.clickSetMyStoreOnFirstCard();
            Assert.assertTrue(true, "Fallback Set My Store button worked");
        } catch (Exception e) {
            Assert.fail("Fallback Set My Store button did not work");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
