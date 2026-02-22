package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    private By findAStoreLinkByLinkText = By.linkText("Find a Store");
    private By findAStoreLinkByCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findAStoreLinkByXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFindAStoreLinkByLinkText() {
        return driver.findElement(findAStoreLinkByLinkText);
    }

    public WebElement getFindAStoreLinkByCss() {
        return driver.findElement(findAStoreLinkByCss);
    }

    public WebElement getFindAStoreLinkByXpath() {
        return driver.findElement(findAStoreLinkByXpath);
    }

    public void clickFindAStore() {
        if (driver.findElements(findAStoreLinkByLinkText).size() > 0) {
            driver.findElement(findAStoreLinkByLinkText).click();
        } else if (driver.findElements(findAStoreLinkByCss).size() > 0) {
            driver.findElement(findAStoreLinkByCss).click();
        } else {
            driver.findElement(findAStoreLinkByXpath).click();
        }
    }
}
