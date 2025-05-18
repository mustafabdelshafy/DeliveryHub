package org.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    public ElementActions(WebDriver driver){}

    @Step("Sending data: {data} to the element: {locator}")
    public static void sendData(WebDriver driver, By locator,String data)
    {
        Waits.waitForElementVisible(driver,locator);
        Scrolling.scrollToElement(driver,locator);
        findElement(driver, locator).sendKeys(data);
    }
    @Step("Uploading file: {filePath} to the input: {locator}")
    public static void uploadFile(WebDriver driver, By locator, String filePath) {
        WebElement fileInput = findElement(driver, locator);

        // Force it to be visible if hidden
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", fileInput);

        fileInput.sendKeys(filePath);
    }

    @Step("Click on Element : {locator}")
    public static void clickElement(WebDriver driver, By locator) {
        try {
            Waits.waitForElementClickable(driver, locator);
            Scrolling.scrollToElement(driver, locator);
            findElement(driver, locator).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // fallback using JavaScript click
            WebElement element = findElement(driver, locator);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    @Step("Get Text from the Element: {locator}")
    public static String getText(WebDriver driver,By locator)
    {
        Waits.waitForElementVisible(driver,locator);
        Scrolling.scrollToElement(driver,locator);
        return findElement(driver, locator).getText();
    }
    public static WebElement findElement(WebDriver driver,By locator)
    {
        return driver.findElement(locator);
    }

}
