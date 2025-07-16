package org.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    private Waits() {}

    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("❌ Element not found after 30s: " + locator);
            throw e;
        }
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(driver1 -> {
                    WebElement element = waitForElementPresent(driver, locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            LogsUtil.error("❌ Element not clickable after 15s: " + locator);
            throw e;
        }
    }


    public static void waitForTextToChange(WebDriver driver, By locator, String oldText) {
        LogsUtil.info("⏳ Waiting for text to change from: " + oldText);
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> {
                    try {
                        String currentText = d.findElement(locator).getText().trim();
                        LogsUtil.info("🔁 Current text: " + currentText);
                        return !currentText.equals(oldText);
                    } catch (Exception e) {
                        return false;
                    }
                });
        LogsUtil.info("✅ Text changed successfully");
    }

    public static void waitForNewTextToAppear(WebDriver driver, By locator, String previousText) {
        LogsUtil.info("⏳ Waiting for new text to appear...");
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> {
                    try {
                        String currentText = d.findElement(locator).getText().trim();
                        LogsUtil.info("🔁 Checking current text: " + currentText);
                        return previousText == null || !currentText.equals(previousText);
                    } catch (Exception e) {
                        return previousText == null;
                    }
                });
        LogsUtil.info("✅ New text appeared successfully");
    }
    public static void waitForMatOptionClickable(WebDriver driver, By locator) {
        LogsUtil.info("⏳ Waiting for mat-option to be clickable: " + locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // تأكد إن العنصر ظاهر
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        // تأكد إن عنصر البحث داخل الـ dropdown اختفى (لو موجود)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("input.mat-select-search-input")));
        } catch (TimeoutException e) {
            LogsUtil.warn("⚠️ Search input still visible. Proceeding anyway.");
        }

        // تأكد إن العنصر قابل للكليك فعلاً
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        LogsUtil.info("✅ mat-option is clickable: " + locator);
    }
    public static void waitForOverlayToDisappear(WebDriver driver) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cdk-overlay-backdrop")));
            LogsUtil.info("✅ Overlay/backdrop disappeared.");
        } catch (TimeoutException e) {
            LogsUtil.warn("⚠️ Overlay/backdrop still visible after timeout.");
        }
    }


}
