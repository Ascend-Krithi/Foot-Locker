# Foot Locker Automation Framework

## Overview
This is a Selenium TestNG automation framework for Foot Locker web application testing.

## Prerequisites
- Java 17
- Maven 3.6+
- Chrome browser

## Project Structure
```
root
├─ .github/workflows/selenium-tests.yml
├─ src/
│  ├─ main/java/com/fl/automation/
│  │  ├─ core/DriverFactory.java
│  │  ├─ helpers/StoreLocatorHelper.java
│  │  ├─ pages/HomePage.java
│  │  └─ utils/
│  │     ├─ ExtentManager.java
│  │     └─ ScreenshotUtil.java
│  └─ test/java/com/fl/automation/
│     ├─ core/BaseTest.java
│     ├─ listeners/TestListener.java
│     └─ tests/
│        ├─ TS001_TC001_.java
│        ├─ TS001_TC002_.java
│        ├─ TS001_TC003_.java
│        ├─ TS001_TC004_.java
│        ├─ TS001_TC005_.java
│        ├─ TS001_TC006_.java
│        └─ TS001_TC007_.java
├─ pom.xml
└─ README.md
```

## Running Tests

### Local Execution
```bash
mvn clean test
```

### CI/CD
Tests run automatically on push/PR to main or develop branches via GitHub Actions.

## Reports
- Extent Report: `test-output/ExtentReport.html`
- Screenshots: `test-output/screenshots/`

## Framework Features
- Selenium 4.21.0 with WebDriverManager
- TestNG 7.10.2 for test orchestration
- Extent Reports 5.1.1 for HTML reporting
- Page Object Model design pattern
- Strict locator policy with fallback mechanisms
- Explicit waits (40-60s)
- JavaScript click fallbacks
- CI-safe Chrome options (headless mode)
- Screenshot capture on test failure

## Test Coverage
- Store Locator functionality
- Find a Store feature
- Store search and selection
- Preferred store persistence