package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.utils.BrowserUtils;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[type='search']");
    private By storeCards = By.cssSelector("[data-qa='location']");
    private By storeAddress = By.cssSelector("[data-qa='address']");
    private By setMyStoreBtn = By.xpath(".//button[contains(.,'Set My Store')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForStore(String location) {
        WebElement search = driver.findElement(searchInput);
        BrowserUtils.type(driver, search, location);
        search.submit();
    }

    public boolean verifyStoreResultsPresent() {
        List<WebElement> stores = driver.findElements(storeCards);
        return stores.size() > 0;
    }

    public boolean verifyStoreAddressContains(String addressPart) {
        List<WebElement> stores = driver.findElements(storeCards);
        for (WebElement store : stores) {
            WebElement address = store.findElement(storeAddress);
            if (address.getText().contains(addressPart)) {
                return true;
            }
        }
        return false;
    }

    public void setFirstStoreAsMyStore() {
        List<WebElement> stores = driver.findElements(storeCards);
        if (!stores.isEmpty()) {
            WebElement setBtn = stores.get(0).findElement(setMyStoreBtn);
            BrowserUtils.click(driver, setBtn);
        }
    }
}