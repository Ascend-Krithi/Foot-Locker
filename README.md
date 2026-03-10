# Foot Locker Automation Framework

## Overview
This is a Selenium TestNG automation framework for Foot Locker web application testing.

## Technology Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- Maven 3.x
- ExtentReports 5.1.1
- WebDriverManager 5.9.2

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

### Run Specific Test Suite
```bash
mvn clean test -Dtest=TS001_*
```

## CI/CD
Tests run automatically on push/PR to main branch via GitHub Actions.

## Reports
- ExtentReport: `test-output/ExtentReport.html`
- Screenshots: `test-output/screenshots/`
- TestNG Report: `test-output/index.html`

## Key Features
- Selenium Manager for automatic driver management
- Robust locator fallback strategy
- ExtentReports integration
- Screenshot capture on failure
- CI/CD ready with GitHub Actions
- Video recording in CI pipeline
