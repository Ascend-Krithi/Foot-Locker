# Foot Locker Automation Framework

## Overview
Enterprise-grade Java Selenium TestNG framework for Foot Locker e-commerce testing with strict locator policy enforcement, GitHub Actions CI/CD, and comprehensive reporting.

## Technology Stack
- **Java:** 17
- **Selenium:** 4.21.0
- **TestNG:** 7.10.2
- **WebDriverManager:** 5.9.2
- **ExtentReports:** 5.1.1
- **Build Tool:** Maven 3.x

## Project Structure
```
root/
├── .github/workflows/selenium-tests.yml
├── src/main/java/com/fl/automation/
│   ├── core/DriverFactory.java
│   ├── helpers/StoreLocatorHelper.java
│   ├── pages/HomePage.java
│   └── utils/
│       ├── ExtentManager.java
│       └── ScreenshotUtil.java
├── src/test/java/com/fl/automation/
│   ├── core/BaseTest.java
│   ├── listeners/TestListener.java
│   └── tests/
├── src/test/resources/testng.xml
└── pom.xml
```

## Strict Locator Policy
- All locators defined ONLY in Page/Helper classes
- Exact fallback order enforced from knowledge base
- No locator creation or modification allowed
- Tests must never contain By locators

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
Tests run automatically on:
- Push to main/develop branches
- Pull requests to main
- Manual workflow dispatch

## Reporting
- **ExtentReports:** `test-output/ExtentReport.html`
- **Screenshots:** `screenshots/` (on failure)
- **CI Artifacts:** Uploaded automatically

## Coding Standards
- WebDriverWait with 40-60s timeout (no Thread.sleep)
- JS click fallback for reliability
- TestNG assertions only
- CI-safe Chrome options

## Contact
QA Automation Team
