package org.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class ElementActions {
    public ElementActions(WebDriver driver){}

    @Step("Sending data: {data} to the element: {locator}")
    public static void sendData(WebDriver driver, By locator, String data) {
        Waits.waitForElementVisible(driver, locator);
        WebElement element = driver.findElement(locator);
        Scrolling.scrollToElement(driver, element);

        // 🧹 Clear any existing text
        try {
            element.clear();

            // ⏳ Sometimes clear() doesn't remove all content, so force it
            if (!element.getAttribute("value").isEmpty()) {
                element.sendKeys(Keys.CONTROL + "a");
                element.sendKeys(Keys.DELETE);
            }
        } catch (Exception e) {
            // Fallback to JS if needed
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
        }

        // 📝 Send new data
        element.sendKeys(data);
    }

    @Step("Uploading file: {filePath} to the input: {locator}")
    public static void uploadFile(WebDriver driver, By locator, String filePath) {
        WebElement fileInput = findElement(driver, locator);

        // Force it to be visible if hidden
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", fileInput);

        fileInput.sendKeys(filePath);
    }
    @Step("Clicking on the element: {locator}")
    public static void clickElement(WebDriver driver, By locator) {
        try {
            Waits.waitForElementClickable(driver, locator);
            WebElement element = driver.findElement(locator); // جلب العنصر مرة واحدة فقط
            Scrolling.scrollToElement(driver, element);       // تمرير نفس العنصر
            element.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            WebElement element = driver.findElement(locator); // fallback في حالة الخطأ
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


    @Step("Get Text from the Element: {locator}")
    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        WebElement element = driver.findElement(locator);
        Scrolling.scrollToElement(driver, element);
        return element.getText();
    }
    public static WebElement findElement(WebDriver driver,By locator)
    {
        return driver.findElement(locator);
    }

}
