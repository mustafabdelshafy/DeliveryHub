package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.swaglabs.utils.*;

public class HomePage {
    //code
    //variables
    private WebDriver driver;
    JsonUtils testData;
    private final By pickup=By.xpath("//mat-expansion-panel-header[contains(., 'Pick-up')]");
    private final By Delivery=By.xpath("//mat-panel-title[contains(., 'Delivery')]");
    private final By customerName1=By.xpath("(//input[@formcontrolname='customerId'])[1]");
    private final By customerName2=By.xpath("(//input[@formcontrolname='customerId'])[2]");
    private final By customerPhone1=By.xpath("(//input[@formcontrolname='phone'])[1]");
    private final By customerPhone2=By.xpath("(//input[@formcontrolname='phone'])[2]");
    private final By governorate1=By.xpath("(//mat-select[@name='governorate'])[1]");
    private final By governorate2=By.xpath("(//mat-select[@name='governorate'])[2]");
    private final By selectGovernorate=By.xpath("//mat-option[.//span[text()=' Asma Governorate ']]");
    private final By area1=By.xpath("(//mat-select[@name='area'])[1]");
    private final By area2=By.xpath("(//mat-select[@name='area'])[2]");
    private final By selectArea=By.id("mat-option-457");
    private final By block =By.name("block");
    private final By selectBlock=By.id("mat-option-625");
    private final By Street=By.id("mat-select-value-13");
    private final By selectStreet=By.id("mat-option-613");


    //constructor
    public HomePage (WebDriver driver)
    {
        this.driver=driver;
    }

    //Locators
    private final By createOrderButton=By.cssSelector("button.btn-create-task");

    //actions
    public HomePage navigateToHomePage()
    {
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }

    public HomePage clickingCreateOrder()
    {
        LogsUtil.info("Clicking on Create order");

        ElementActions.clickElement(driver, createOrderButton);

        return this;
    }
    public HomePage fillPickupTask()
    {
        LogsUtil.info("Filling the Create order form");
        ElementActions.sendData(driver,customerName,testData.getJsonData("customer-names.customer1.name"));
        ElementActions.sendData(driver,customerPhone,testData.getJsonData("customer-names.customer1.phoneNumber"));
        ElementActions.clickElement(driver,governorate);
        ElementActions.clickElement(driver,selectGovernorate);
        ElementActions.clickElement(driver,area);
        ElementActions.clickElement(driver,selectArea);
        ElementActions.clickElement(driver,block);
        ElementActions.clickElement(driver,selectBlock);
        ElementActions.clickElement(driver,Street);
        ElementActions.clickElement(driver,selectStreet);
        ElementActions.clickElement(driver,pickup);
        return this;
    }
    public HomePage fillDeliveryTask()
    {
        LogsUtil.info("Filling the Create order form");
        ElementActions.sendData(driver,customerName,testData.getJsonData("customer-names.customer2.name"));
        ElementActions.sendData(driver,customerPhone,testData.getJsonData("customer-names.customer2.phoneNumber"));
        ElementActions.clickElement(driver,governorate);
        ElementActions.clickElement(driver,selectGovernorate);
        ElementActions.clickElement(driver,area);
        ElementActions.clickElement(driver,selectArea);
        ElementActions.clickElement(driver,block);
        ElementActions.clickElement(driver,selectBlock);
        ElementActions.clickElement(driver,Street);
        ElementActions.clickElement(driver,selectStreet);
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
