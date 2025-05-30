package org.swaglabs.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.swaglabs.utils.PropertiesUtils;

public class BrowserFactory {
    private BrowserFactory() {}

    public static WebDriver getBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return new ChromeDriver(getChromeOptions());
            case "firefox":
                return new FirefoxDriver(getFirefoxOptions());
            default:
                return new EdgeDriver(getEdgeOptions());
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")) {
            options.addArguments("--headless");}
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized"); // Open in maximized mode
        options.addArguments("--incognito"); // Private browsing mode
        options.addArguments("--disable-popup-blocking"); // Disable popups
        options.addArguments("--remote-allow-origins=*"); // Prevent remote debugging issues
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // Removes automation banner
        options.setExperimentalOption("useAutomationExtension", false);
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
            if (!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")) {
                options.addArguments("--headless");
            }
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("-private"); // Private mode
        options.setAcceptInsecureCerts(true); // Ignore SSL errors
        options.addPreference("dom.webnotifications.enabled", false); // Block notifications
        options.addPreference("signon.rememberSignons", false); // Disable "Save Password" popup
        options.addPreference("dom.webdriver.enabled", false); // Remove automation banner
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")) {
            options.addArguments("--headless");
        }
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        return options;
    }
}
