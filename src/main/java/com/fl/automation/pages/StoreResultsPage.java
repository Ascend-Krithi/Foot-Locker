package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private By storeResults = By.cssSelector(".store-result");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public int getStoreCount() {
        List<WebElement> stores = driver.findElements(storeResults);
        return stores.size();
    }
}
