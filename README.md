# Foot Locker Selenium Automation Framework

## Overview
This is a Selenium-based automation framework for testing the Foot Locker web application. It uses TestNG for test execution, ExtentReports for reporting, and follows the Page Object Model (POM) design pattern.

## Prerequisites
- Java 17+
- Maven 3.8+
- Google Chrome (latest stable version)

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
├─ src/test/resources/testng.xml
├─ pom.xml
└─ README.md
```

## Running Tests Locally
```bash
mvn clean test
```

## Running Tests in Headless Mode
```bash
mvn clean test -Dheadless=true
```

## CI/CD
Tests run automatically on push/PR to `main` or `develop` branches via GitHub Actions.

## Reports
- **Extent Reports**: `target/extent-reports/extent-report.html`
- **Screenshots** (on failure): `target/screenshots/`

## Coding Standards
- Follow Page Object Model (POM)
- Use explicit waits (WebDriverWait)
- Use JavaScript click fallback when needed
- All locators must be in Page/Helper classes
- Tests extend BaseTest and use TestNG assertions