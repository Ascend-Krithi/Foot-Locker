package com.fl.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreLocatorPage {
    private WebDriver driver;

    @FindBy(id = "searchBox")
    private WebElement searchBox;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearch(String query) {
        searchBox.clear();
        searchBox.sendKeys(query);
    }

    public void clickSearch() {
        searchButton.click();
    }
}