# Foot Locker Automation Framework

A robust Selenium-based test automation framework for Foot Locker web application testing, built with Java 17, TestNG, and following Page Object Model (POM) design pattern.

## 🏗️ Project Structure

```
root
├─ .github/
│  └─ workflows/
│     └─ selenium-tests.yml          # GitHub Actions CI/CD pipeline
├─ src/
│  ├─ main/
│  │  └─ java/
│  │     └─ com/
│  │        └─ fl/
│  │           └─ automation/
│  │              ├─ core/
│  │              │  └─ DriverFactory.java      # WebDriver initialization
│  │              ├─ helpers/
│  │              │  └─ StoreLocatorHelper.java # Store locator utilities
│  │              ├─ pages/
│  │              │  └─ HomePage.java           # Home page object model
│  │              └─ utils/
│  │                 ├─ ExtentManager.java      # Report management
│  │                 └─ ScreenshotUtil.java     # Screenshot capture utility
│  └─ test/
│     ├─ java/
│     │  └─ com/
│     │     └─ fl/
│     │        └─ automation/
│     │           ├─ core/
│     │           │  └─ BaseTest.java           # Base test configuration
│     │           ├─ listeners/
│     │           │  └─ TestListener.java       # TestNG listener for reporting
│     │           └─ tests/
│     │              ├─ TS001_TC001_.java       # Test: Validate Find Store Popup
│     │              ├─ TS001_TC002_.java       # Test: Validate Store Locator Textbox
│     │              ├─ TS001_TC003_.java       # Test: Validate Store Search
│     │              ├─ TS001_TC004_.java       # Test: Validate Store Address
│     │              ├─ TS001_TC005_.java       # Test: Validate Set My Store
│     │              ├─ TS001_TC006_.java       # Test: Validate Store Confirmation
│     │              └─ TS001_TC007_.java       # Test: Validate Store Persistence
│     └─ resources/
│        └─ testng.xml                          # TestNG suite configuration
├─ README.md
└─ pom.xml                                      # Maven dependencies & build config
```

## 🚀 Technology Stack

- **Java:** 17
- **Build Tool:** Maven 3.x
- **Test Framework:** TestNG 7.10.2
- **Automation Tool:** Selenium WebDriver 4.21.0
- **Driver Management:** WebDriverManager 5.9.2
- **Reporting:** ExtentReports 5.1.1
- **Code Simplification:** Lombok 1.18.32
- **CI/CD:** GitHub Actions

## 📋 Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher
- Google Chrome browser (latest stable version)
- Git

## 🔧 Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd footlocker-automation
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run Tests Locally
```bash
# Run all tests
mvn clean test

# Run with custom browser (headless mode)
mvn clean test -Dheadless=true

# Run specific test suite
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

## 🧪 Test Execution

### Local Execution
Tests run in **headed mode** by default for local debugging. To run in headless mode:
```bash
mvn clean test -Dheadless=true
```

### CI/CD Execution (GitHub Actions)
Tests automatically run in **headless mode** on every push/pull request to `main` branch.

The workflow:
1. Sets up Java 17
2. Installs Chrome browser
3. Runs Maven tests with headless Chrome
4. Uploads test reports as artifacts

## 📊 Test Reports

After test execution, reports are generated in:
- **ExtentReports HTML:** `test-output/ExtentReport.html`
- **TestNG Reports:** `test-output/index.html`
- **Screenshots (on failure):** `test-output/screenshots/`

## 🎯 Framework Features

### Strict Locator Policy
- All locators defined **only in Page/Helper classes**
- Follows exact fallback order for reliability
- No locators in test classes

### Robust Wait Strategy
- Uses `WebDriverWait` with explicit conditions
- No `Thread.sleep()` usage
- JavaScript click fallbacks for stability

### CI/CD Ready
- Headless Chrome configuration
- Selenium Manager for automatic driver provisioning
- GitHub Actions integration

### Comprehensive Reporting
- ExtentReports with screenshots on failure
- TestNG HTML reports
- Detailed test execution logs

## 🏛️ Design Patterns

- **Page Object Model (POM):** Separates page structure from test logic
- **Factory Pattern:** `DriverFactory` for WebDriver initialization
- **Helper Pattern:** `StoreLocatorHelper` for reusable store locator operations
- **Listener Pattern:** `TestListener` for test lifecycle hooks

## 🔐 Best Practices

✅ **Locators:** Defined only in Page/Helper classes  
✅ **Waits:** Explicit waits with WebDriverWait  
✅ **Assertions:** TestNG assertions for validation  
✅ **Reporting:** ExtentReports with screenshots  
✅ **CI/CD:** Headless execution in pipelines  
✅ **Code Quality:** Lombok for cleaner code  

## 📝 Test Scenarios

| Test ID | Description |
|---------|-------------|
| TS001_TC001 | Validate "Find a Store" popup appears with "Select My Store" link |
| TS001_TC002 | Validate Store Locator popup contains Location textbox and Search button |
| TS001_TC003 | Validate store search functionality with "Boston, MA" |
| TS001_TC004 | Validate specific store address appears in search results |
| TS001_TC005 | Validate "Set My Store" functionality for Boston location |
| TS001_TC006 | Validate selected store confirmation and persistence |
| TS001_TC007 | Validate selected store persists across page navigation |

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 Contact

For questions or support, please contact the Automation Team.

---

**Note:** This framework follows enterprise-grade automation standards with strict locator policies, robust wait strategies, and CI/CD integration for reliable test execution.