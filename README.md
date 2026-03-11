# Foot Locker Automation Framework

## Overview
Selenium TestNG automation framework for Foot Locker web application testing.

## Prerequisites
- Java 17+
- Maven 3.8+
- Chrome browser (latest)

## Setup
```bash
git clone <repository-url>
cd footlocker-automation
mvn clean install
```

## Running Tests

### Local Execution
```bash
mvn clean test
```

### Headless Mode
```bash
mvn clean test -Dheadless=true
```

### CI Execution
Tests run automatically on push/PR via GitHub Actions.

## Reports
- **Extent Report**: `test-output/ExtentReport.html`
- **Screenshots**: `test-output/screenshots/`

## Project Structure
```
src/
├── main/java/com/fl/automation/
│   ├── core/DriverFactory.java
│   ├── helpers/StoreLocatorHelper.java
│   ├── pages/HomePage.java
│   └── utils/
│       ├── ExtentManager.java
│       └── ScreenshotUtil.java
└── test/java/com/fl/automation/
    ├── core/BaseTest.java
    ├── listeners/TestListener.java
    └── tests/
        ├── TS001_TC001_SCRUM17166.java
        ├── TS001_TC002_SCRUM17166.java
        └── TS001_TC003_SCRUM17166.java
```

## Troubleshooting

### Chrome Driver Issues
- Selenium 4.21+ uses Selenium Manager (no manual driver setup needed)
- Set `CHROME_PATH` environment variable if using custom Chrome location

### Headless Mode Failures
- Ensure CI flags are enabled: `--no-sandbox`, `--disable-dev-shm-usage`
- Check window size: `--window-size=1920,1080`

### Timeout Issues
- Default wait: 40-60 seconds
- Adjust in BaseTest if needed

## Contact
For issues or questions, contact the QA Automation team.