# Foot Locker Automation Framework

## Overview
This is a Selenium TestNG automation framework for testing the Foot Locker web application.

## Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome browser

## Project Structure
```
root/
├── src/
│   ├── main/java/com/fl/automation/
│   │   ├── core/              # Core framework components
│   │   ├── helpers/           # Helper classes
│   │   ├── pages/             # Page Objects
│   │   └── utils/             # Utility classes
│   └── test/
│       ├── java/com/fl/automation/
│       │   ├── core/          # BaseTest
│       │   ├── listeners/     # TestNG listeners
│       │   └── tests/         # Test scripts
│       └── resources/
│           └── testng.xml     # TestNG configuration
├── pom.xml
└── README.md
```

## Running Tests

### Command Line
```bash
mvn clean test
```

### Specific Test Suite
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

## Reports
- ExtentReports: `test-output/ExtentReport.html`
- Screenshots: `test-output/screenshots/`

## Framework Features
- Page Object Model (POM)
- WebDriverManager for automatic driver management
- ExtentReports for detailed test reporting
- Screenshot capture on test failure
- Explicit waits with fallback strategies
- Multi-fallback XPath locators for resilience

## CI/CD Integration
This framework is designed to run in GitHub Actions with headless Chrome.
