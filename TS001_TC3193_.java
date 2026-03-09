/**
 * TC_ID: 3193
 * Description: Validate Find Store navigation from HomePage
 */
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS001_TC3193_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindStoreNavigation() {
        HomePage home = new HomePage();
        home.clickFindStore();
        String url = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("stores.footlocker.com"), "Should navigate to store locator");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
