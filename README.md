# Foot Locker Automation Framework

## Overview
This is a complete Java Selenium TestNG automation framework for testing Foot Locker's store locator functionality. The framework implements 7 test cases covering the complete user journey from finding a store to verifying store persistence across navigation.

## Technology Stack
- **Java**: 17
- **Selenium WebDriver**: 4.21.0
- **TestNG**: 7.10.2
- **ExtentReports**: 5.1.1
- **WebDriverManager**: 5.9.2
- **Lombok**: 1.18.32
- **Maven**: Build and dependency management
- **Maven Surefire Plugin**: 3.2.5

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
│       ├── java/
│       │   └── com/fl/automation/
│       │       ├── core/
│       │       │   └── BaseTest.java
│       │       ├── listeners/
│       │       │   └── TestListener.java
│       │       └── tests/
│       │           ├── TS001_TC001.java
│       │           ├── TS001_TC002.java
│       │           ├── TS001_TC003.java
│       │           ├── TS001_TC004.java
│       │           ├── TS001_TC005.java
│       │           ├── TS001_TC006.java
│       │           └── TS001_TC007.java
│       └── resources/
│           └── testng.xml
├── pom.xml
└── README.md
```

## Test Cases
1. **TS001_TC001**: Launch homepage, click Find a Store, verify popup message and Select My Store link
2. **TS001_TC002**: Click Find a Store, click Select My Store, verify Location textbox and Search button
3. **TS001_TC003**: Search for stores in Boston MA, verify results displayed
4. **TS001_TC004**: Search Boston MA, verify store at 375 Washington Street is visible
5. **TS001_TC005**: Search Boston MA, click Set My Store for 375 Washington Street
6. **TS001_TC006**: Set 375 Washington Street as preferred, verify confirmation indicator and header display
7. **TS001_TC007**: Set 375 Washington Street as preferred, navigate to sneakers page, verify store persists

## Key Features
- **Page Object Model (POM)**: Clean separation of test logic and page elements
- **Explicit Waits**: Robust synchronization with 40-60 second timeouts
- **Fallback Locators**: Multiple locator strategies for reliability
- **JavaScript Click Fallback**: Handles click interception issues
- **ExtentReports Integration**: Detailed HTML test reports
- **Screenshot on Failure**: Automatic screenshot capture for failed tests
- **CI/CD Ready**: Headless Chrome configuration for pipeline execution
- **Cookie Consent Handling**: Automatic acceptance of cookie banners

## Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Chrome browser installed

## Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test suite:
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run in headless mode (default):
Tests run in headless mode by default for CI/CD compatibility.

### View Reports:
After test execution, open `test-output/ExtentReport.html` in a browser to view detailed test results.

## Configuration
- **Base URL**: https://www.footlocker.com/
- **Browser**: Chrome (headless)
- **Implicit Wait**: None (using explicit waits only)
- **Explicit Wait Timeout**: 40-60 seconds
- **Screenshot Directory**: test-output/screenshots/

## Design Patterns
- **Page Object Model**: Encapsulates page elements and actions
- **Factory Pattern**: DriverFactory for WebDriver instantiation
- **Singleton Pattern**: ExtentManager for report management
- **Helper Pattern**: StoreLocatorHelper for complex store operations

## Best Practices Implemented
- No hardcoded waits (Thread.sleep)
- Locators only in Page/Helper classes
- TestNG assertions for validation
- Comprehensive error handling
- Clean code structure
- Detailed logging and reporting

## CI/CD Integration
The framework is configured for seamless CI/CD integration with:
- Headless Chrome execution
- No-sandbox mode for containerized environments
- Disable-dev-shm-usage for Docker compatibility
- Maven Surefire plugin for test execution
- ExtentReports for test result visualization

## Troubleshooting
- **Element not found**: Framework uses multiple fallback locators
- **Click intercepted**: Automatic JavaScript click fallback
- **Timeout issues**: Adjust wait times in Page/Helper classes
- **Cookie banner**: Automatically handled in HomePage constructor

## Metadata
- **Running Instance ID**: run_20250608_001
- **Pipeline ID**: pipeline_footlocker_001
- **Framework Version**: 1.0-SNAPSHOT
- **Last Updated**: 2025-06-08

## Support
For issues or questions, please refer to the project documentation or contact the automation team.

---
**Note**: This framework is designed for reliable, maintainable, and scalable test automation following industry best practices.