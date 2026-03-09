/**
 * TC_ID: 3196
 * Description: Validate Select My Store link is present on Store Locator Page
 */
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC3196_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSelectMyStoreLinkPresent() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        boolean present = false;
        try {
            locator.clickSelectMyStore();
            present = true;
        } catch (Exception e) {
            present = false;
        }
        Assert.assertTrue(present, "Select My Store link should be present");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
