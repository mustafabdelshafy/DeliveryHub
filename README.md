# DeliveryHub Automation Framework

This is a **Hybrid Test Automation Framework** with the following characteristics:

**Page Object Model (POM)**
Clear separation of pages (LoginPage, DashboardPage, etc.) from test logic.

**Modular Utilities**
Reusable utility classes (BrowserActions, ElementActions, ScreenshotsUtils) indicate a modular design.

**TestNG Integration**
Presence of TestNGListeners confirms TestNG as the test runner.

**Allure Reporting**
AllureUtils suggests integration with Allure for advanced reporting.

**Cross-Browser Support**
BrowserFactory with Chrome/Edge/Firefox implementations shows multi-browser capability.

**Soft Assertions**
CustomSoftAssertion class enables non-hard-failure validations.



## Features

- **Selenium WebDriver 4.28.0** for browser automation
- **TestNG** test framework for test execution and reporting
- **Page Object Model (POM)** design pattern for better maintainability
- **Allure Reports** for beautiful and detailed test reporting
- **Maven** for dependency management
- **Log4j 2** for logging
- **Java Faker** for test data generation
- **Gson** for JSON parsing

## Prerequisites

- Java 21 or higher
- Maven 3.5.2 or higher
- Chrome/Firefox/Edge browser installed (depending on your test requirements)
- Internet connection (for downloading dependencies)

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd DeliveryHub
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run Tests

Run all tests:
```bash
mvn test
```

Run tests with a specific testng.xml suite:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### 4. Generate Allure Report
```bash
mvn allure:serve
```

## Project Structure

```
DeliveryHub/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/swaglabs/
│   │   │       ├── drivers/     # WebDriver management
│   │   │       ├── listeners/   # TestNG listeners
│   │   │       ├── pages/       # Page Object classes
│   │   │       └── utils/       # Utility classes
│   │   └── resources/
│   │       └── environment.properties  # Environment configuration
│   └── test/
│       ├── java/
│       │   └── com/swagles/tests/  # Test classes
│       └── resources/
│           └── test-data.json     # Test data
├── test-outputs/                # Test execution outputs
├── pom.xml                     # Maven configuration
└── README.md                   # This file
```

## Configuration

Edit the `src/main/resources/environment.properties` file to configure:
- Browser settings
- Application URL
- Timeouts
- Other environment-specific settings

## Writing Tests

1. Create a new test class in `src/test/java/com/swagles/tests/`
2. Extend the `BaseTest` class
3. Use the `@Test` annotation from TestNG for test methods
4. Use the page objects from `src/main/java/org/swaglabs/pages/`

Example test method:
```java
@Test(description = "Verify successful login")
@Severity(SeverityLevel.CRITICAL)
@Description("Test Description: Verify user can login with valid credentials")
public void testSuccessfulLogin() {
    // Test implementation
}
```

## Reporting

Test execution reports are generated in the `test-output` directory. To view the Allure report:

```bash
mvn allure:serve
```

## Adding Dependencies

Add new dependencies to the `pom.xml` file and run:

```bash
mvn clean install
```

## Best Practices

1. Follow the Page Object Model pattern
2. Use meaningful test method names
3. Add proper test descriptions and severity levels
4. Keep test data separate from test logic
5. Use proper wait strategies
6. Implement proper exception handling
7. Add appropriate logging

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
