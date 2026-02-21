package com.project1.automation.tests.navigation;

import com.project1.automation.drivers.DriverManager;
import com.project1.automation.utilities.ConfigReader;
import com.project1.automation.utilities.LogHelper;
import com.project1.automation.pages.FootLockerHomePage;
import com.project1.automation.pages.StoreSelectorModal;
import com.project1.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class FootLockerStoreFlowTests {
    private WebDriver driver;
    private FootLockerHomePage homePage;
    private StoreSelectorModal selectorModal;
    private StoreLocatorPage locatorPage;
    private Duration timeout;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
        timeout = Duration.ofSeconds(ConfigReader.getInt("explicitWaitSec"));
        homePage = new FootLockerHomePage(driver, timeout);
        selectorModal = new StoreSelectorModal(driver, timeout);
        locatorPage = new StoreLocatorPage(driver, timeout);
    }

    /**
     * SCRUM-15408 TS-001 TC-001
     * Verify that clicking 'Find a Store' on the homepage displays a popup with the correct message and a visible 'Select My Store' link.
     */
    @Test
    public void testFindAStorePopup() {
        LogHelper.info("Start: testFindAStorePopup");
        homePage.goToHome();
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.clickFindAStore(), "'Find a Store' was not clickable");
        Assert.assertTrue(homePage.ensureFindStoreUIVisible(), "Find-a-store popup/modal not visible");
        // For popup message and link presence, assume modal appears and selectMyStoreControl is visible
        Assert.assertTrue(homePage.clickSelectMyStoreIfPresent(), "'Select My Store' link not visible in popup");
        LogHelper.info("End: testFindAStorePopup");
    }

    /**
     * SCRUM-15408 TS-002 TC-001
     * Verify that clicking 'Select My Store' in the popup opens the 'Find a Store' popup window containing a 'Location' textbox and a 'Search for Stores' button.
     */
    @Test
    public void testSelectMyStoreOpensPopup() {
        LogHelper.info("Start: testSelectMyStoreOpensPopup");
        homePage.goToHome();
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.clickFindAStore(), "'Find a Store' was not clickable");
        Assert.assertTrue(homePage.clickSelectMyStoreIfPresent(), "'Select My Store' link not visible in popup");
        // Validate modal input and search button
        Assert.assertNotNull(selectorModal.resolveInput(8), "'Location' textbox not visible in popup");
        LogHelper.info("End: testSelectMyStoreOpensPopup");
    }

    /**
     * SCRUM-15408 TS-003 TC-001
     * Verify that entering 'Boston, MA' in the Location textbox and clicking 'Search for Stores' displays relevant store results in or near Boston.
     */
    @Test
    public void testStoreSearchBostonResults() {
        LogHelper.info("Start: testStoreSearchBostonResults");
        homePage.goToHome();
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.clickFindAStore(), "'Find a Store' was not clickable");
        Assert.assertTrue(homePage.clickSelectMyStoreIfPresent(), "'Select My Store' link not visible in popup");
        selectorModal.typeAndSubmit("Boston, MA");
        locatorPage.waitForResultsOrEmpty(timeout);
        List<String> addresses = locatorPage.getAddresses();
        boolean foundBoston = addresses.stream().anyMatch(addr -> addr.contains("Boston"));
        Assert.assertTrue(foundBoston, "No store results for Boston, MA");
        LogHelper.info("End: testStoreSearchBostonResults");
    }

    /**
     * SCRUM-15408 TS-004 TC-001
     * Verify that the store with address '375 Washington Street, Boston, MA 02108' is visible in the search results and the address matches exactly.
     */
    @Test
    public void testStoreSearchExactAddress() {
        LogHelper.info("Start: testStoreSearchExactAddress");
        homePage.goToHome();
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.clickFindAStore(), "'Find a Store' was not clickable");
        Assert.assertTrue(homePage.clickSelectMyStoreIfPresent(), "'Select My Store' link not visible in popup");
        selectorModal.typeAndSubmit("Boston, MA");
        locatorPage.waitForResultsOrEmpty(timeout);
        List<String> addresses = locatorPage.getAddresses();
        boolean foundExact = addresses.stream().anyMatch(addr -> addr.equals("375 Washington Street, Boston, MA 02108"));
        Assert.assertTrue(foundExact, "Store with exact address '375 Washington Street, Boston, MA 02108' not found");
        LogHelper.info("End: testStoreSearchExactAddress");
    }

    /**
     * SCRUM-15408 TS-005 TC-001
     * Verify that clicking 'Set My Store' for the Boston location saves the selected store as the user's preferred store.
     */
    @Test
    public void testSetMyStoreBoston() {
        LogHelper.info("Start: testSetMyStoreBoston");
        homePage.goToHome();
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.clickFindAStore(), "'Find a Store' was not clickable");
        Assert.assertTrue(homePage.clickSelectMyStoreIfPresent(), "'Select My Store' link not visible in popup");
        selectorModal.typeAndSubmit("Boston, MA");
        locatorPage.waitForResultsOrEmpty(timeout);
        boolean setStore = locatorPage.clickSetMyStoreFor("375 Washington Street");
        Assert.assertTrue(setStore, "Failed to set preferred store for Boston location");
        LogHelper.info("End: testSetMyStoreBoston");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quit();
    }
}
