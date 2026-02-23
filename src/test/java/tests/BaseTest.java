package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserUtils;
import utils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        BrowserUtils.navigateToBaseUrl(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
