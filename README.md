# Foot Locker Selenium Automation Framework

## Overview
This is a Selenium TestNG automation framework for testing the Foot Locker web application store locator functionality.

## Technology Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- Maven 3.x
- ExtentReports 5.1.1
- Lombok 1.18.32

## Project Structure
```
root
├── .github/workflows/selenium-tests.yml
├── src/
│   ├── main/java/com/fl/automation/
│   │   ├── core/DriverFactory.java
│   │   ├── helpers/StoreLocatorHelper.java
│   │   ├── pages/HomePage.java
│   │   └── utils/
│   │       ├── ExtentManager.java
│   │       └── ScreenshotUtil.java
│   └── test/java/com/fl/automation/
│       ├── core/BaseTest.java
│       ├── listeners/TestListener.java
│       └── tests/
│           ├── TS001_TC001_.java
│           ├── TS001_TC002_.java
│           ├── TS001_TC003_.java
│           ├── TS001_TC004_.java
│           ├── TS001_TC005_.java
│           ├── TS001_TC006_.java
│           └── TS001_TC007_.java
├── pom.xml
└── README.md
```

## Prerequisites
- JDK 17 or higher
- Maven 3.6+
- Chrome browser (latest version)

## Running Tests

### Local Execution
```bash
mvn clean test
```

### Headless Mode
```bash
mvn clean test -Dheadless=true
```

### Run Specific Test
```bash
mvn clean test -Dtest=TS001_TC001_
```

## CI/CD Integration
The framework includes GitHub Actions workflow configuration for automated test execution on push and pull requests.

## Test Reports
Extent Reports are generated in the `test-output/` directory after test execution.

## Framework Features
- Page Object Model (POM) design pattern
- Strict locator policy with fallback mechanisms
- Explicit waits with WebDriverWait
- JavaScript click fallbacks
- Screenshot capture on test failure
- ExtentReports integration
- CI-ready Chrome options
- Selenium Manager for driver provisioning

## Test Coverage
- Store locator functionality
- Search and filter stores
- Set preferred store
- Store persistence across navigation

## Contact
For questions or issues, please contact the QA Automation team.