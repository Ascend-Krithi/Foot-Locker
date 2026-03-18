public void enterLocation(String city) {
    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
    input.clear();
    input.sendKeys(city);
    System.out.println("[INFO] Typed city: " + city);

    // ✅ Wait longer for Google Places autocomplete to load
    try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

    // ✅ Try clicking first autocomplete suggestion
    List<WebElement> suggestions = driver.findElements(By.xpath(
        "//*[contains(@class,'pac-item')] | " +
        "//*[contains(@class,'suggestion')] | " +
        "//*[contains(@class,'Suggestion')] | " +
        "//*[contains(@class,'autocomplete')] | " +
        "//*[@role='option'] | " +
        "//*[@role='listbox']//*[@role='option'] | " +
        "//*[contains(@class,'StoreLocator')]//li[contains(@class,'item')]"
    ));

    System.out.println("[DEBUG] Suggestions found: " + suggestions.size());

    boolean clicked = false;
    for (WebElement s : suggestions) {
        try {
            if (s.isDisplayed() && !s.getText().trim().isEmpty()) {
                System.out.println("[INFO] Selecting suggestion: '" + s.getText().trim() + "'");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", s);
                clicked = true;
                Thread.sleep(1000);
                break;
            }
        } catch (Exception ignored) {}
    }

    if (!clicked) {
        System.out.println("[WARN] No suggestion clicked, pressing ENTER.");
        input.sendKeys(Keys.ENTER);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    // Wait for error to clear
    try {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.invisibilityOfElementLocated(By.id("StoreLocatorErrors")));
    } catch (Exception ignored) {}
}
