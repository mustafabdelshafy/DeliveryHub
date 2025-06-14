package org.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BrowserActions {
    public BrowserActions(WebDriver driver){}
    @Step("Navigate to URL: {url}")
    public static void navigateToURL(WebDriver driver,String url)
    {
        driver.get(url);
        LogsUtil.info("Navigated to URL: ",url);
    }
    @Step("Waiting until the page is fully loaded")
    public static void waitUntilPageLoaded(WebDriver driver) {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }


    // Get current URL
    @Step("Get Current URL")
    public static String getCurrentURL(WebDriver driver)
    {
        LogsUtil.info("Current URL", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    //Get page title
    @Step("Getting Page Title")
    public static String getPageTitle(WebDriver driver)
    {
        LogsUtil.info("Page Title: ",driver.getTitle());
        return driver.getTitle();
    }

    //refresh page
    @Step("Refreshing the page")
    public static void refreshPage(WebDriver driver)
    {
        LogsUtil.info("Refreshing the page");
        driver.navigate().refresh();
    }

    //close browser
    public static void closeBrowser(WebDriver driver)
    {
        LogsUtil.info("Closing the browser");
        driver.quit();
    }
}
