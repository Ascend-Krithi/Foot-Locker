# Foot Locker Selenium Automation Framework

## Overview
Complete Java Selenium TestNG framework for Foot Locker automation with 47 test cases from TestRail.

## Technology Stack
- Java 17
- Selenium WebDriver 4.21.0
- TestNG 7.10.2
- Extent Reports 5.1.1
- WebDriverManager 5.9.2
- Lombok 1.18.32
- Maven 3.9+

## Project Structure
```
footlocker-selenium-automation/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── fl/
│   │               └── automation/
│   │                   ├── core/
│   │                   │   └── DriverFactory.java
│   │                   ├── helpers/
│   │                   │   └── StoreLocatorHelper.java
│   │                   ├── pages/
│   │                   │   └── HomePage.java
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
│       │                   ├── TS001_TC001_VerifyFindAStorePopup.java
│       │                   └── ... (46 more test files)
│       └── resources/
│           └── testng.xml
```

## Test Cases Coverage
- **Store Locator Tests (TC3193-TC3195, TC4170-TC4175)**: 10 tests
- **Eco Home Hub Tests (TC4104-TC4123)**: 20 tests
- **Marketplace Tests (TC4139-TC4156)**: 18 tests

## Setup Instructions

### Prerequisites
1. Java 17 or higher installed
2. Maven 3.9+ installed
3. Chrome browser installed

### Installation
```bash
git clone <repository-url>
cd footlocker-selenium-automation
mvn clean install
```

### Running Tests

#### Run all tests
```bash
mvn clean test
```

#### Run specific test suite
```bash
mvn clean test -Dtest=TS001_TC001_VerifyFindAStorePopup
```

#### Run with TestNG XML
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

## Configuration

### Browser Configuration
- Default: Chrome (headless mode)
- Options: --headless=new, --no-sandbox, --disable-dev-shm-usage
- WebDriverManager handles driver binaries automatically

### Wait Configuration
- Explicit waits: 40-60 seconds
- No Thread.sleep() used
- JavaScript click fallback for stale elements

## Reporting
- Extent Reports generated in `test-output/ExtentReport.html`
- Screenshots captured on test failures
- TestNG reports in `test-output/` directory

## Locator Strategy
All locators use fallback chains for reliability:
1. Primary locator (most specific)
2. Secondary locator (alternative)
3. Tertiary locator (XPath fallback)

## Best Practices
- Page Object Model (POM) design pattern
- No locators in test classes
- Explicit waits only
- TestNG assertions
- Extent Reports integration
- Screenshot on failure

## CI/CD Integration
Framework is CI/CD ready with:
- Maven build lifecycle
- TestNG XML suite configuration
- Headless browser execution
- Automated reporting

## Troubleshooting

### Common Issues
1. **ChromeDriver not found**: WebDriverManager handles this automatically
2. **Element not found**: Check wait times and locator fallback chains
3. **Tests failing in headless**: Verify viewport size and element visibility

## Contact
For issues or questions, please contact the automation team.

## License
Proprietary - Foot Locker Internal Use Only