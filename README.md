# Foot Locker Automation Framework

## Overview
This is a complete Selenium TestNG automation framework for Foot Locker store locator functionality. The framework implements 7 test cases covering the complete user journey from finding a store to verifying store persistence.

## Technology Stack
- **Java**: 17
- **Selenium WebDriver**: 4.21.0
- **WebDriverManager**: 5.9.2
- **TestNG**: 7.10.2
- **ExtentReports**: 5.1.1
- **Lombok**: 1.18.32
- **Maven**: Build and dependency management

## Project Structure
```
footlocker-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/fl/automation/
│   │           ├── core/
│   │           │   └── DriverFactory.java
│   │           ├── pages/
│   │           │   └── HomePage.java
│   │           ├── helpers/
│   │           │   └── StoreLocatorHelper.java
│   │           └── utils/
│   │               ├── ExtentManager.java
│   │               └── ScreenshotUtil.java
│   └── test/
│       └── java/
│           └── com/fl/automation/
│               ├── core/
│               │   └── BaseTest.java
│               ├── listeners/
│               │   └── TestListener.java
│               └── tests/
│                   ├── TS001_TC001_LaunchHomepageVerifyFindStore.java
│                   ├── TS001_TC002_VerifyStoreLocatorPopup.java
│                   ├── TS001_TC003_SearchStoresBoston.java
│                   ├── TS001_TC004_VerifyBostonStoreAddress.java
│                   ├── TS001_TC005_SetBostonStoreAsPreferred.java
│                   ├── TS001_TC006_VerifyStoreConfirmation.java
│                   └── TS001_TC007_VerifyStorePersistence.java
│       └── resources/
│           └── testng.xml
├── pom.xml
└── README.md
```

## Test Cases
1. **TC4200**: Launch homepage, click Find a Store, verify popup and Select My Store link
2. **TC4201**: Click Find a Store, click Select My Store, verify Location textbox and Search button
3. **TC4202**: Open Find a Store popup, enter Boston MA, click Search, verify store results
4. **TC4203**: Search Boston MA, verify store at 375 Washington Street is visible
5. **TC4204**: Search Boston MA, locate 375 Washington Street store, click Set My Store
6. **TC4205**: Set 375 Washington Street as preferred store, verify confirmation
7. **TC4206**: Set 375 Washington Street as preferred, navigate to sneakers, verify persistence

## Key Features
- **Page Object Model (POM)**: Clean separation of test logic and page elements
- **Locator Fallback Strategy**: Multiple locator strategies for robust element identification
- **WebDriverWait**: Explicit waits (40-60 seconds) for reliable synchronization
- **ExtentReports Integration**: Detailed HTML test reports with screenshots
- **TestNG Listeners**: Automatic screenshot capture on test failure
- **CI/CD Ready**: Chrome options configured for headless execution
- **Cookie Consent Handling**: Automatic acceptance of cookie banners

## Running Tests

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Chrome browser installed

### Execute Tests
```bash
# Run all tests
mvn clean test

# Run specific test suite
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### View Reports
After test execution, open the ExtentReport:
```
test-output/ExtentReport.html
```

Screenshots for failed tests are saved in:
```
test-output/screenshots/
```

## Configuration
- **Base URL**: https://www.footlocker.com
- **Implicit Wait**: 10 seconds
- **Explicit Wait**: 40 seconds
- **Browser**: Chrome (incognito mode, maximized)

## CI/CD Integration
The framework is configured for CI/CD pipelines with:
- Headless Chrome options
- No sandbox mode
- Disabled GPU acceleration
- Remote allow origins enabled

## Maintenance
- All locators are centralized in Page Objects and Helper classes
- Fallback locator strategies ensure resilience to UI changes
- WebDriverManager handles driver binaries automatically
- No hardcoded waits (Thread.sleep) used

## Contact
For issues or questions, please refer to the project documentation or contact the automation team.