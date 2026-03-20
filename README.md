# Foot Locker Automation Framework

## Overview
This is a Selenium TestNG automation framework for testing Foot Locker's store locator functionality.

## Technology Stack
- Java 17
- Maven
- Selenium WebDriver 4.21.0
- TestNG 7.10.2
- WebDriverManager 5.9.2
- ExtentReports 5.1.1
- Lombok 1.18.32

## Project Structure
```
footlocker-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── fl/
│   │               └── automation/
│   │                   ├── core/
│   │                   │   └── DriverFactory.java
│   │                   ├── pages/
│   │                   │   └── HomePage.java
│   │                   ├── helpers/
│   │                   │   └── StoreLocatorHelper.java
│   │                   └── utils/
│   │                       ├── ExtentManager.java
│   │                       └── ScreenshotUtil.java
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── fl/
│       │           └── automation/
│       │               ├── core/
│       │               │   └── BaseTest.java
│       │               ├── listeners/
│       │               │   └── TestListener.java
│       │               └── tests/
│       │                   ├── TS001_TC001_LaunchHomepageVerifyFindStore.java
│       │                   ├── TS001_TC002_VerifySelectMyStoreAndLocationTextbox.java
│       │                   ├── TS001_TC003_SearchBostonMAVerifyResults.java
│       │                   ├── TS001_TC004_VerifyWashingtonStreetStoreVisible.java
│       │                   ├── TS001_TC005_ClickSetMyStoreForWashingtonStreet.java
│       │                   ├── TS001_TC006_VerifyStoreConfirmationIndicator.java
│       │                   └── TS001_TC007_VerifyStorePersistsOnSneakersPage.java
│       └── resources/
│           └── testng.xml
└── pom.xml
```

## Test Cases
1. **TC_4200**: Launch homepage and verify Find a Store link
2. **TC_4201**: Verify Select My Store button and Location textbox
3. **TC_4202**: Search for Boston MA and verify results
4. **TC_4203**: Verify 375 Washington Street store is visible
5. **TC_4204**: Click Set My Store for 375 Washington Street
6. **TC_4205**: Verify store confirmation indicator
7. **TC_4206**: Verify store persists on sneakers page

## Setup Instructions
1. Ensure Java 17 is installed
2. Ensure Maven is installed
3. Clone the repository
4. Navigate to project directory

## Running Tests
```bash
mvn clean test
```

## Reports
- ExtentReports: `test-output/ExtentReport.html`
- Screenshots: `test-output/screenshots/`

## Framework Features
- Page Object Model (POM) design pattern
- WebDriverManager for automatic driver management
- ExtentReports for detailed test reporting
- Screenshot capture on test failure
- TestNG for test execution and assertions
- Thread-safe WebDriver management
- Explicit waits with fallback to JavaScript execution

## Configuration
- Browser: Chrome (headless mode disabled by default)
- Implicit Wait: 10 seconds
- Explicit Wait: 40-60 seconds
- Window Size: 1920x1080

## Author
Automation Team

## Version
1.0-SNAPSHOT