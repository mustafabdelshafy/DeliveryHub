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
//    @Step("Clicking on the element: {locator}")
//    public static void clickElement(WebDriver driver, By locator) {
//        try {
//            Waits.waitForElementClickable(driver, locator);
//            WebElement element = driver.findElement(locator); // جلب العنصر مرة واحدة فقط
//            Scrolling.scrollToElement(driver, element);       // تمرير نفس العنصر
//            element.click();
//        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
//            WebElement element = driver.findElement(locator); // fallback في حالة الخطأ
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
//        }
//    }
@Step("Clicking on the element: {locator}")
public static void clickElement(WebDriver driver, By locator) {
    try {
        // ⛔ انتظر اختفاء أي overlay قبل أي تفاعل
        Waits.waitForOverlayToDisappear(driver);

        // ⏳ انتظر حتى يكون العنصر قابل للنقر
        WebElement element = Waits.waitForElementClickable(driver, locator);

        // 🔽 Scroll للعنصر ليكون ظاهر
        Scrolling.scrollToElement(driver, element);

        try {
            // 👆 محاولة بكليك عادي
            element.click();
            LogsUtil.info("✅ Element clicked successfully: " + locator);

        } catch (WebDriverException e) {
            // ⚠️ fallback لو الكليك العادي فشل لأي سبب
            LogsUtil.warn("⚠️ Regular click failed for element: " + locator + ". Trying JavaScript click. Error: " + e.getMessage());

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            LogsUtil.info("✅ Element clicked via JavaScript: " + locator);
        }

    } catch (Exception e) {
        // 🛑 لو فيه مشكلة كبيرة زي إن العنصر مش موجود أصلاً
        LogsUtil.error("❌ Failed to click on element: " + locator + ". Error: " + e.getMessage());
        throw e;
    }
}

    @Step("Forcibly clicking on the element via JavaScript: {locator}")
    public static void forceClickElement(WebDriver driver, By locator) {
        try {
            // ⏳ تأكد إن مفيش overlay شغال يغطي العنصر
            Waits.waitForOverlayToDisappear(driver);

            // 🕵️‍♂️ تأكد إن العنصر موجود
            WebElement element = Waits.waitForElementPresent(driver, locator);

            // 🔽 Scroll عشان يكون ظاهر في الشاشة
            Scrolling.scrollToElement(driver, element);

            // 🖱️ نفّذ كليك بالـ JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            LogsUtil.info("✅ Forced JS click executed on: " + locator);

        } catch (Exception e) {
            LogsUtil.error("❌ Failed to force click element: " + locator + ". Error: " + e.getMessage());
            throw e;
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
