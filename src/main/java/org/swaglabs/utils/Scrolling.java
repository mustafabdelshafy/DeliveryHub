package org.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {
    private Scrolling()
    {
    }
    @Step("Scrolling to Element; {0}")
    public static void scrollToElement(WebDriver driver, By locator)
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",
                ElementActions.findElement(driver, locator));
    }
}
