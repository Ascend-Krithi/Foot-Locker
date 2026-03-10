# Foot Locker Selenium Automation Framework

## Overview
This is a Maven-based Selenium TestNG automation framework for Foot Locker web application testing.

## Technology Stack
- Java 17
- Selenium 4.21.0+
- TestNG 7.10.2+
- WebDriverManager 5.9.2+
- ExtentReports 5.1.1
- Maven 3.x

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
│  └─ test/
│     ├─ java/com/fl/automation/
│     │  ├─ core/BaseTest.java
│     │  ├─ listeners/TestListener.java
│     │  └─ tests/ (35 test classes)
│     └─ resources/testng.xml
├─ README.md
└─ pom.xml
```

## Setup
1. Install Java 17
2. Install Maven 3.x
3. Clone the repository
4. Run: `mvn clean test`

## Running Tests
- Local: `mvn clean test`
- Headless: `mvn clean test -Dheadless=true`
- CI/CD: Automated via GitHub Actions

## Reports
- ExtentReports: `test-output/ExtentReport.html`
- Screenshots: `test-output/screenshots/`

## Test Cases
Total: 35 test cases covering Store Locator functionality

## CI/CD
Automated testing via GitHub Actions on push/pull request to main branch.