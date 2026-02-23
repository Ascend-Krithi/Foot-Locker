package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement link;
        try {
            link = WaitUtils.waitForElementVisible(driver, findStoreHeader);
        } catch (Exception e1) {
            try {
                link = WaitUtils.waitForElementVisible(driver, findStoreHeaderCss);
            } catch (Exception e2) {
                link = WaitUtils.waitForElementVisible(driver, findStoreHeaderXpath);
            }
        }
        link.click();
    }
}
