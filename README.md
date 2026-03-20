# Foot Locker Automation Framework

Selenium TestNG automation framework for Foot Locker web application store locator functionality.

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
│       │                   ├── TS001_TC002_VerifyStoreLocatorPopup.java
│       │                   ├── TS001_TC003_SearchStoresBoston.java
│       │                   ├── TS001_TC004_VerifyBostonStoreAddress.java
│       │                   ├── TS001_TC005_SetBostonStoreAsPreferred.java
│       │                   ├── TS001_TC006_VerifyStoreConfirmation.java
│       │                   └── TS001_TC007_VerifyStorePersistence.java
│       └── resources/
│           └── testng.xml
└── pom.xml
```

## Prerequisites

- Java 17
- Maven 3.6+
- Chrome browser

## Dependencies

- Selenium Java 4.21.0
- WebDriverManager 5.9.2
- TestNG 7.10.2
- ExtentReports 5.1.1
- Lombok 1.18.32

## Running Tests

```bash
mvn clean test
```

## Test Coverage

- TC001: Launch homepage and verify Find a Store functionality
- TC002: Verify Store Locator popup elements
- TC003: Search for stores in Boston, MA
- TC004: Verify specific Boston store address
- TC005: Set Boston store as preferred
- TC006: Verify store confirmation indicator
- TC007: Verify store persistence across pages

## Framework Features

- Page Object Model design pattern
- WebDriverManager for automatic driver management
- ExtentReports for test reporting
- TestNG for test execution and assertions
- Explicit waits with fallback mechanisms
- CI/CD ready with GitHub Actions support
