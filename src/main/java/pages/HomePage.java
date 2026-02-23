package pages;

import core.BrowserUtils;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private final By findStore = By.linkText("Find a Store");
    private final By findStoreFallback1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findStoreFallback2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, findStore)) {
            BrowserUtils.click(driver, findStore);
        } else if (BrowserUtils.isDisplayed(driver, findStoreFallback1)) {
            BrowserUtils.click(driver, findStoreFallback1);
        } else {
            BrowserUtils.click(driver, findStoreFallback2);
        }
    }
}
