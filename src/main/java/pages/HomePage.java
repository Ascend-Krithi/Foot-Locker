package pages;

import core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;
    private final By findStoreHeader = By.linkText("Find a Store");
    private final By findStoreHeaderAlt1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findStoreHeaderAlt2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement el;
        try {
            el = WaitUtils.waitForClickable(driver, findStoreHeader);
        } catch (Exception e1) {
            try {
                el = WaitUtils.waitForClickable(driver, findStoreHeaderAlt1);
            } catch (Exception e2) {
                el = WaitUtils.waitForClickable(driver, findStoreHeaderAlt2);
            }
        }
        el.click();
    }
}
