package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyHPProfilePage {
    private WebDriver driver;

    private By profileIcon = By.xpath("//*[contains(@content-desc,'Profile') or contains(@text,'Profile') or contains(@resource-id,'profile')]");
    private By sendFeedbackButton = By.xpath("//*[contains(@text,'Send Feedback') or contains(@content-desc,'Send Feedback')]");

    public MyHPProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void tapProfileIcon() {
        BrowserUtils.click(driver, profileIcon);
    }

    public void tapSendFeedback() {
        BrowserUtils.click(driver, sendFeedbackButton);
    }
}