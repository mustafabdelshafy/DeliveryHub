package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.swaglabs.utils.*;

public class HomePage {
    //code
    //variables
    private WebDriver driver;

    //constructor
    public HomePage (WebDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By cartIcon=By.cssSelector("[[data-test='shopping-cart-link']]");

    //actions
    public HomePage navigateToHomePage()
    {
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }

    public HomePage addSpecificProductToCart(String productName)
    {
        LogsUtil.info("Adding " + productName + " to cart");
        // Corrected XPath with quotes around productName
        By productLocator = By.xpath("//div[.='" + productName + "']");

        // Use RelativeLocator to find the button below the product name
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(productLocator);

        ElementActions.clickElement(driver, addToCartButton);

        return this;
    }
    public HomePage assertProductAddedToCart(String productName)
    {
        By productLocator = By.xpath("//div[.='" + productName + "']");

        // Use RelativeLocator to find the button below the product name
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(productLocator);
        String actualValue=ElementActions.getText(driver,addToCartButton);
        LogsUtil.info("Actual value: "+actualValue);
        Validations.validateEquals(actualValue,"Remove","Product not added to cart");
        LogsUtil.info(productName+"added to cart successfully");
        return this;
    }

}
