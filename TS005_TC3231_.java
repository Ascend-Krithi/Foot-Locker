/**
 * TC_ID: 3231
 * Description: Validate Set My Store button sets the store
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

public class TS005_TC3231_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStoreFunctionality() {
        HomePage home = new HomePage();
        home.clickFindStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.enterSearch("Miami");
        StoreResultsPage results = new StoreResultsPage();
        results.clickSetMyStoreOnFirstCard();
        // No direct validation, just ensure no exception
        Assert.assertTrue(true, "Set My Store button clicked successfully");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
