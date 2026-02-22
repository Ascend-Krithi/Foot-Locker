package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import java.time.Duration;

public class StoreLocatorTest {
    private WebDriver driver;
    private HomePage homePage;
    private StoreLocatorPage storeLocatorPage;

    @BeforeClass
    public void setUp() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.footlocker.com");
        homePage = new HomePage(driver);
        storeLocatorPage = new StoreLocatorPage(driver);
    }

    @Test
    public void testFindAStoreLink() {
        homePage.clickFindAStore();
        Assert.assertTrue(driver.getCurrentUrl().contains("stores.footlocker.com"), "Did not navigate to store locator");
    }

    @Test(dependsOnMethods = "testFindAStoreLink")
    public void testSelectMyStorePopup() {
        Assert.assertTrue(storeLocatorPage.getPopup().isDisplayed(), "Store locator popup not displayed");
    }

    @Test(dependsOnMethods = "testSelectMyStorePopup")
    public void testSelectMyStore() {
        storeLocatorPage.selectMyStore();
        // Add assertion or further steps as needed
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
