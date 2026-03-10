# Foot Locker Selenium TestNG Automation Framework

This project is a complete Selenium TestNG automation framework for Foot Locker's store locator features, using Java 17, Selenium 4.21.0, WebDriverManager 5.9.2, TestNG 7.10.2, ExtentReports 5.1.1, and Lombok 1.18.32.

## Structure
- **src/main/java/com/fl/automation/core/DriverFactory.java**: WebDriver setup/teardown
- **src/main/java/com/fl/automation/pages/HomePage.java**: Page Object Model for Home Page
- **src/main/java/com/fl/automation/helpers/StoreLocatorHelper.java**: Store locator actions
- **src/main/java/com/fl/automation/utils/ExtentManager.java**: ExtentReports integration
- **src/main/java/com/fl/automation/utils/ScreenshotUtil.java**: Screenshot utility
- **src/test/java/com/fl/automation/core/BaseTest.java**: Base test class
- **src/test/java/com/fl/automation/listeners/TestListener.java**: TestNG listener for reporting
- **src/test/java/com/fl/automation/tests/**: 35 test classes (see TestRail mapping)
- **src/test/resources/testng.xml**: TestNG suite

## Running Tests
```bash
mvn clean test
```

## CI
See `.github/workflows/selenium-tests.yml` for GitHub Actions integration.
