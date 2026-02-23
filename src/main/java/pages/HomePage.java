package pages;

import core.BrowserUtils;
import core.WaitUtils;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickFindAStore() {
        WebElement el = null;
        if (BrowserUtils.isDisplayed(driver.findElement(findStoreHeader))) {
            el = driver.findElement(findStoreHeader);
        } else if (BrowserUtils.isDisplayed(driver.findElement(findStoreHeaderCss))) {
            el = driver.findElement(findStoreHeaderCss);
        } else {
            el = driver.findElement(findStoreHeaderXpath);
        }
        WaitUtils.waitForElementToBeClickable(driver, el);
        BrowserUtils.click(el);
    }
}
