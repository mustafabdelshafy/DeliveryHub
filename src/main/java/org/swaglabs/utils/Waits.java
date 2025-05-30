package org.swaglabs.utils;

import org.openqa.selenium.By;
<<<<<<< HEAD
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
=======
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    //present - visible - clickable
    private Waits ()
    {

    }

    //wait for element to be present
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
<<<<<<< HEAD
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> driver1.findElement(locator));
    }

=======
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("❌ Element not found after 30s: " + locator);
            throw e;
        }
    }

    /*public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(driver1 -> driver1.findElement(locator));
    }*/

>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
    // wait for element to be visible
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementPresent(driver, locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    // wait for element to be clickable
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
<<<<<<< HEAD
        return new WebDriverWait(driver, Duration.ofSeconds(10))
=======
        return new WebDriverWait(driver, Duration.ofSeconds(15))
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
                .until(driver1 -> {
                    WebElement element = waitForElementVisible(driver, locator);
                    return element.isEnabled() ? element : null;
                });
    }
}
