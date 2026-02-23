package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyHPFeedbackPage {
    private WebDriver driver;

    private By starRating1 = By.xpath("//*[contains(@content-desc,'1 star') or contains(@text,'1')]");
    private By starRating2 = By.xpath("//*[contains(@content-desc,'2 star') or contains(@text,'2')]");
    private By starRating3 = By.xpath("//*[contains(@content-desc,'3 star') or contains(@text,'3')]");
    private By starRating4 = By.xpath("//*[contains(@content-desc,'4 star') or contains(@text,'4')]");
    private By starRating5 = By.xpath("//*[contains(@content-desc,'5 star') or contains(@text,'5')]");
    private By whyOpenedAppDropdown = By.xpath("//*[contains(@text,'Why did you open the app today?')]");
    private By feedbackRelatedToDropdown = By.xpath("//*[contains(@text,'What\\'s your feedback related to?')]");
    private By dropdownOption = By.xpath("//*[contains(@class,'option') or contains(@resource-id,'option')]");
    private By sendFeedbackButton = By.xpath("//*[contains(@text,'Send Feedback') and contains(@class,'button')]");
    private By confirmationMessage = By.xpath("//*[contains(@text,'Thank you') or contains(@text,'submitted') or contains(@text,'received')]");

    public MyHPFeedbackPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectStarRating(int stars) {
        By starLocator;
        switch (stars) {
            case 1:
                starLocator = starRating1;
                break;
            case 2:
                starLocator = starRating2;
                break;
            case 3:
                starLocator = starRating3;
                break;
            case 4:
                starLocator = starRating4;
                break;
            case 5:
                starLocator = starRating5;
                break;
            default:
                throw new IllegalArgumentException("Invalid star rating: " + stars);
        }
        BrowserUtils.click(driver, starLocator);
    }

    public void deselectStarRating(int stars) {
        selectStarRating(stars);
    }

    public boolean isStarRatingHighlighted(int stars) {
        By starLocator;
        switch (stars) {
            case 1:
                starLocator = starRating1;
                break;
            case 2:
                starLocator = starRating2;
                break;
            case 3:
                starLocator = starRating3;
                break;
            case 4:
                starLocator = starRating4;
                break;
            case 5:
                starLocator = starRating5;
                break;
            default:
                return false;
        }
        return BrowserUtils.isDisplayed(driver, starLocator);
    }

    public void tapWhyOpenedAppDropdown() {
        BrowserUtils.click(driver, whyOpenedAppDropdown);
    }

    public void selectWhyOpenedAppOption(String option) {
        tapWhyOpenedAppDropdown();
        By optionLocator = By.xpath("//*[contains(@text,'" + option + "')]");
        BrowserUtils.click(driver, optionLocator);
    }

    public void tapFeedbackRelatedToDropdown() {
        BrowserUtils.click(driver, feedbackRelatedToDropdown);
    }

    public void selectFeedbackRelatedToOption(String option) {
        tapFeedbackRelatedToDropdown();
        By optionLocator = By.xpath("//*[contains(@text,'" + option + "')]");
        BrowserUtils.click(driver, optionLocator);
    }

    public void tapSendFeedbackButton() {
        BrowserUtils.click(driver, sendFeedbackButton);
    }

    public boolean isConfirmationMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, confirmationMessage);
    }
}