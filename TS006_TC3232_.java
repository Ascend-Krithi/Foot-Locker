/**
 * TC_ID: 3232
 * Description: Validate store address is not empty after search
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

public class TS006_TC3232_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreAddressNotEmpty() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("Dallas");
        StoreResultsPage results = new StoreResultsPage();
        String address = results.getFirstStoreAddress();
        Assert.assertNotNull(address, "Store address should be present");
        Assert.assertFalse(address.isEmpty(), "Store address should not be empty");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
