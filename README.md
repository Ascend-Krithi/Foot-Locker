# Foot Locker Selenium Automation Framework

## Overview
This is a Maven-based Selenium TestNG automation framework for Foot Locker web application testing.

## Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome browser (latest version)

## Project Structure
```
root/
├─ .github/workflows/
│  └─ selenium-tests.yml
├─ src/
│  ├─ main/java/com/fl/automation/
│  │  ├─ core/
│  │  │  └─ DriverFactory.java
│  │  ├─ helpers/
│  │  │  └─ StoreLocatorHelper.java
│  │  ├─ pages/
│  │  │  └─ HomePage.java
│  │  └─ utils/
│  │     ├─ ExtentManager.java
│  │     └─ ScreenshotUtil.java
│  └─ test/
│     ├─ java/com/fl/automation/
│     │  ├─ core/
│     │  │  └─ BaseTest.java
│     │  ├─ listeners/
│     │  │  └─ TestListener.java
│     │  └─ tests/
│     │     └─ (35 test classes)
│     └─ resources/
│        └─ testng.xml
├─ README.md
└─ pom.xml
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

### CI/CD Execution
Tests run automatically via GitHub Actions on push/pull request.

## Test Reports
Extent Reports are generated in `test-output/ExtentReport.html`

## Framework Features
- Page Object Model (POM) design pattern
- Selenium 4.21+ with Selenium Manager (no WebDriverManager needed)
- TestNG for test execution and assertions
- Extent Reports for detailed test reporting
- GitHub Actions CI/CD integration
- Robust locator strategy with fallback mechanisms
- Explicit waits with long timeouts for CI stability
- JavaScript click fallbacks for reliability

## Test Coverage
35 automated test cases covering:
- Store locator functionality
- Store search and selection
- Store preference persistence
- Error handling and validation

## Contact
For questions or issues, please contact the QA Automation team.