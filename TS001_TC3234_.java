/**
 * TC_ID: 3234
 * Description: Validate navigation to Store Locator from HomePage using fallback locator
 */
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC3234_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindStoreNavigationFallback() {
        HomePage home = new HomePage();
        home.clickFindStore();
        String url = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("stores.footlocker.com"), "Should navigate to store locator using fallback locator");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
