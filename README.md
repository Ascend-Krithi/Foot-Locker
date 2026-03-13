# Foot Locker Automation Framework

## Overview
Selenium TestNG automation framework for Foot Locker store locator functionality testing.

## Test Coverage
- **TS001_TC001**: Verify 'Find a Store' link and 'Select My Store' visibility
- **TS001_TC002**: Verify store locator popup elements (Location textbox, Search button)
- **TS001_TC003**: Search for stores in Boston, MA and verify results
- **TS001_TC004**: Verify specific store address (375 Washington Street, Boston, MA 02108)
- **TS001_TC005**: Set preferred store location
- **TS001_TC006**: Verify store selection confirmation and header display
- **TS001_TC007**: Verify store preference persists across navigation

## Prerequisites
- Java 17+
- Maven 3.6+
- Chrome browser

## Dependencies
- Selenium 4.21.0
- WebDriverManager 5.9.2
- TestNG 7.10.2
- ExtentReports 5.1.1
- Lombok 1.18.32

## Project Structure
```
footlocker-automation/
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
│       │                   ├── TS001_TC001.java
│       │                   ├── TS001_TC002.java
│       │                   ├── TS001_TC003.java
│       │                   ├── TS001_TC004.java
│       │                   ├── TS001_TC005.java
│       │                   ├── TS001_TC006.java
│       │                   └── TS001_TC007.java
│       └── resources/
│           └── testng.xml
```

## Execution

### Run all tests:
```bash
mvn clean test
```

### Run specific test suite:
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run in headless mode:
```bash
mvn clean test -Dheadless=true
```

## Reports
- ExtentReports: `test-output/ExtentReport.html`
- TestNG Reports: `test-output/index.html`
- Screenshots: `test-output/screenshots/`

## CI/CD Integration
Framework is configured for CI/CD with:
- Headless Chrome execution
- No-sandbox mode
- Disabled dev-shm-usage
- Automated WebDriver management

## Configuration
- Base URL: https://www.footlocker.com
- Browser: Chrome (headless in CI)
- Implicit Wait: 10 seconds
- Explicit Wait: 40-60 seconds
- Page Load Timeout: 60 seconds

## Author
Automation Team

## License
Proprietary