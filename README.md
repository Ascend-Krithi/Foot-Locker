# Foot Locker Automation Framework

Enterprise-grade Selenium TestNG framework for Foot Locker web application testing.

## 🛠️ Tech Stack
- Java 17
- Selenium 4.21+
- TestNG 7.10+
- Extent Reports 5.1.1
- Maven 3.9+

## 🚀 Quick Start

### Prerequisites
- JDK 17+
- Maven 3.9+

### Run Tests Locally
```bash
mvn clean test
```

### View Reports
- Extent Report: `test-output/ExtentReport.html`
- TestNG Report: `target/surefire-reports/index.html`

## 📁 Project Structure
```
src/
├── main/java/com/fl/automation/
│   ├── core/          # DriverFactory
│   ├── helpers/       # StoreLocatorHelper
│   ├── pages/         # Page Objects
│   └── utils/         # ExtentManager, ScreenshotUtil
└── test/java/com/fl/automation/
    ├── core/          # BaseTest
    ├── listeners/     # TestListener
    └── tests/         # Test Classes
```

## 🔒 Strict Locator Policy
- All `By` locators MUST be in Page/Helper classes
- Tests MUST NOT declare locators
- Use explicit waits (40-60s)
- JS click fallback for stale elements

## 🧪 CI/CD
GitHub Actions workflow runs on push/PR to main/develop.

## 📊 Reporting
- Extent Reports: HTML dashboard
- Screenshots: Auto-captured on failure