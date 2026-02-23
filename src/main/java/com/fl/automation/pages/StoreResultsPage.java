package com.fl.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    @FindBy(css = ".store-result")
    private List<WebElement> storeResults;

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getStoreResultsCount() {
        return storeResults.size();
    }
}