# Foot Locker Automation Framework

## Overview
This is a Java Selenium TestNG automation framework for testing the Foot Locker web application (https://www.footlocker.com/).

## Technology Stack
- **Java**: 17
- **Selenium WebDriver**: 4.21.0
- **TestNG**: 7.10.2
- **Maven**: 3.x
- **Extent Reports**: 5.1.1
- **WebDriverManager**: 5.9.2
- **Lombok**: 1.18.32

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

## Prerequisites
- Java 17 installed
- Maven 3.x installed
- Chrome browser (stable version)

## Running Tests Locally

### Compile the project
```bash
mvn clean compile
```

### Run all tests (headless mode)
```bash
mvn clean test
```

### Run tests in headed mode
```bash
mvn clean test -Dheadless=false
```

### Run specific test suite
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

## CI/CD Pipeline
The framework includes a GitHub Actions workflow (`.github/workflows/selenium-tests.yml`) that:
- Runs on push to main/feature branches and pull requests
- Uses Ubuntu latest with JDK 17
- Installs Chrome stable
- Executes tests with Xvfb virtual display
- Records test execution video
- Uploads artifacts (video, ExtentReport, screenshots)

## Test Reports
After test execution, find the Extent Report at:
```
target/extent-reports/ExtentReport.html
```

## Framework Features
- **Page Object Model (POM)** design pattern
- **Strict Locator Policy** with fallback mechanisms
- **Explicit waits** (40-60 seconds) for element interactions
- **JavaScript click fallbacks** for stubborn elements
- **ExtentReports** integration for detailed test reporting
- **Screenshot capture** on test failures
- **CI-safe Chrome options** for headless execution
- **Selenium Manager** for automatic driver provisioning

## Configuration
- **Base URL**: https://www.footlocker.com/
- **Browser**: Chrome (headless by default in CI)
- **Timeouts**: Page load (90s), Script (30s), Implicit wait (40-60s)

## Contact
For questions or issues, please contact the QA Automation team.