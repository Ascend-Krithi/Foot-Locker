# Foot Locker Automation Framework

Enterprise-grade Selenium TestNG automation framework for Foot Locker web application testing.

## Tech Stack
- Java 17
- Selenium 4.21.0
- TestNG 7.10.2
- Maven 3.x
- ExtentReports 5.1.1
- Lombok 1.18.32

## Project Structure
```
src/
├── main/java/com/fl/automation/
│   ├── core/          # Driver factory and base configurations
│   ├── helpers/       # Business logic helpers
│   ├── pages/         # Page Object Model classes
│   └── utils/         # Utilities (reporting, screenshots)
└── test/java/com/fl/automation/
    ├── core/          # BaseTest class
    ├── listeners/     # TestNG listeners
    └── tests/         # Test classes
```

## Setup & Execution

### Prerequisites
- JDK 17+
- Maven 3.6+
- Chrome browser

### Local Execution
```bash
# Run all tests
mvn clean test

# Run in headed mode
mvn clean test -Dheadless=false

# Run specific test suite
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### CI/CD
Tests run automatically on push/PR to main/develop branches via GitHub Actions.

## Reporting
- ExtentReports: `test-output/ExtentReport.html`
- TestNG Reports: `target/surefire-reports/`
- Screenshots: `test-output/screenshots/` (on failure)

## Coding Standards
- Page Object Model pattern
- Explicit waits (40-60s timeout)
- JS click fallbacks
- TestNG assertions
- No Thread.sleep()
- Selenium Manager for driver provisioning

## CI Configuration
- Headless Chrome with stability flags
- JDK 17 (Temurin distribution)
- Artifact upload for reports and screenshots