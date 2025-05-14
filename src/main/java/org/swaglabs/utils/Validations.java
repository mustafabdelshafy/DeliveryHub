package org.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validations {
    public Validations(WebDriver driver) {
    }
    @Step("Validate True")
    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
    @Step ("Validate False")
    public static void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
    @Step("Validate Equals")
    public static void validateEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Validate Not Equals")
    public static void validateNotEquals(String actual, String expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }
@Step("Validate Page URL")
    public static void validatePageUrl(WebDriver driver, String expected) {
        Assert.assertEquals(BrowserActions.getCurrentURL(driver), expected);
    }
   @Step("Validate Page Title: {expected}")
    public static void validatePageTitle(WebDriver driver, String expected) {
        Assert.assertEquals(BrowserActions.getPageTitle(driver), expected);
    }


}
