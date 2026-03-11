# Foot Locker Automation Framework

Enterprise-grade Selenium TestNG automation framework for Foot Locker web application.

## 🚀 Features

- **Selenium 4.21.0** with Selenium Manager (no WebDriverManager)
- **TestNG 7.10.2** for test orchestration
- **Extent Reports 5.1.1** for rich HTML reporting
- **Page Object Model** with strict locator policy
- **CI/CD ready** with GitHub Actions
- **Headless Chrome** support for pipeline execution

## 📋 Prerequisites

- Java 17+
- Maven 3.8+
- Chrome browser (for local execution)

## 🛠️ Setup

1. Clone the repository
2. Run `mvn clean install`
3. Execute tests: `mvn clean test`

## 📂 Project Structure

```
src/
├── main/java/com/fl/automation/
│   ├── core/          # DriverFactory
│   ├── helpers/       # StoreLocatorHelper
│   ├── pages/         # Page Object classes
│   └── utils/         # ExtentManager, ScreenshotUtil
└── test/java/com/fl/automation/
    ├── core/          # BaseTest
    ├── listeners/     # TestListener
    └── tests/         # Test classes
```

## 🧪 Running Tests

### Local Execution
```bash
mvn clean test
```

### CI/CD Pipeline
Tests run automatically on push/PR to main/develop branches.

## 📊 Reports

- **Extent Report**: `test-output/ExtentReport.html`
- **TestNG Report**: `target/surefire-reports/index.html`
- **Screenshots**: `test-output/screenshots/`

## 🔧 Configuration

- **TestNG Suite**: `src/test/resources/testng.xml`
- **Maven Config**: `pom.xml`
- **CI Workflow**: `.github/workflows/selenium-tests.yml`

## 📖 Coding Standards

- All locators in Page/Helper classes only
- Strict fallback order for locators
- WebDriverWait (40-60s) - no Thread.sleep
- JS click fallback for all click actions
- TestNG assertions only

## 🤝 Contributing

Follow the strict locator policy and coding standards defined in the knowledge base.