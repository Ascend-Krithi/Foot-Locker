/**
 * TC_ID: 3228
 * Description: Validate navigation to Store Locator from Select My Store link
 */
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS002_TC3228_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSelectMyStoreNavigation() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        String url = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("stores.footlocker.com"), "Should navigate to store locator");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
