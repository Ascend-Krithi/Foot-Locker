# Foot Locker Automation Framework

## Overview
TestNG-based Selenium 4.21+ automation framework for Foot Locker web application with strict locator policies and CI/CD integration.

## Prerequisites
- Java 11+
- Maven 3.6+
- Chrome browser (for local runs)

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

### Headless Mode
```bash
mvn clean test -Dheadless=true
```

### Custom Base URL
```bash
mvn clean test -DbaseUrl=https://www.footlocker.com/
```

## CI/CD
Tests run automatically on push/PR to main/master via GitHub Actions.

## Reports
- **ExtentReports**: `test-output/ExtentReport.html`
- **Screenshots**: `test-output/screenshots/`

## Framework Features
- Page Object Model (POM)
- Strict Locator Policy with fallback order
- ExtentReports integration
- Screenshot capture on failure
- Selenium Manager for driver provisioning
- CI/CD ready with GitHub Actions

## Locator Policy
All locators follow strict fallback order defined in STRICT LOCATOR POLICY_KB.docx. Locators are implemented only in Page/Helper classes, never in test classes.

## Support
For issues or questions, contact the QA Automation team.