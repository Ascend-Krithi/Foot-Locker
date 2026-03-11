package com.fl.automation.helpers;

import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class StoreLocatorHelper {
    private HomePage homePage;

    public StoreLocatorHelper(WebDriver driver) {
        this.homePage = new HomePage(driver);
    }

    public void openStoreLocator() {
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
    }

    public boolean verifyStoreLocatorOpened() {
        return homePage.isStoreLocatorPopupOpen();
    }

    public void searchForStores(String location) {
        homePage.enterLocation(location);
    }

    public boolean verifySearchResults() {
        return homePage.isSearchResultsDisplayed();
    }
}