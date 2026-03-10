# Foot Locker Automation Framework

## Overview
This is a Selenium TestNG automation framework for Foot Locker web application testing.

## Technology Stack
- Java 17
- Selenium WebDriver 4.21.0
- TestNG 7.10.2
- Maven 3.x
- Extent Reports 5.1.1

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
│        └─ TS001_TC003_.java
├─ pom.xml
└─ README.md
```

## Running Tests

### Local Execution
```bash
mvn clean test
```

### Headless Mode
```bash
mvn clean test -Dheadless=true
```

### CI/CD
Tests run automatically on push/PR to main branch via GitHub Actions.

## Reports
- Extent Reports: `test-output/ExtentReport.html`
- Screenshots: `test-output/screenshots/`

## Framework Features
- Page Object Model (POM)
- Strict locator policy with fallback mechanisms
- Explicit waits (40-60s)
- JavaScript click fallbacks
- CI-optimized Chrome options
- Selenium Manager for driver provisioning
- Extent Reports integration
- Screenshot capture on failure
