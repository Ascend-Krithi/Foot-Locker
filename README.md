# Foot Locker Automation Framework

## Overview
Selenium TestNG automation framework for Foot Locker web application testing.

## Technology Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- WebDriverManager 5.9.2
- ExtentReports 5.1.1
- Maven

## Project Structure
```
footlocker-automation/
├── src/
│   ├── main/java/com/fl/automation/
│   │   ├── core/
│   │   │   └── DriverFactory.java
│   │   ├── helpers/
│   │   │   └── StoreLocatorHelper.java
│   │   ├── pages/
│   │   │   └── HomePage.java
│   │   └── utils/
│   │       ├── ExtentManager.java
│   │       └── ScreenshotUtil.java
│   └── test/java/com/fl/automation/
│       ├── core/
│       │   └── BaseTest.java
│       ├── listeners/
│       │   └── TestListener.java
│       └── tests/
│           └── TS001_TC00X_*.java
├── src/test/resources/
│   └── testng.xml
└── pom.xml
```

## Running Tests
```bash
mvn clean test
```

## Reports
Extent reports are generated in `test-output/ExtentReport.html`

## CI/CD
Configured for GitHub Actions with headless Chrome execution.