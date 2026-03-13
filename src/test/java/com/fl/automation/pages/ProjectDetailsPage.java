package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProjectDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By projectTitle = By.cssSelector("h1, .project-title");
    private By contactButton = By.cssSelector("button.contact, a.contact");
    private By favoriteButton = By.cssSelector("button.favorite");
    private By shareButton = By.cssSelector("button.share");
    
    public ProjectDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public boolean isProjectDetailsDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(projectTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickContact() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(contactButton));
        clickWithJsFallback(button);
    }
    
    public void clickFavorite() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(favoriteButton));
        clickWithJsFallback(button);
    }
    
    private void clickWithJsFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}