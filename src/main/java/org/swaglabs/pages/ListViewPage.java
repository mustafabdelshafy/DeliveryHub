package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.swaglabs.utils.*;

import static org.swaglabs.utils.PropertiesUtils.getPropertyValue;

public class ListViewPage {
//Variables
     WebDriver driver;

//Constructor
    public ListViewPage(WebDriver driver) {
        this.driver = driver;
    }
    //Locators
    private final By listView = By.xpath("//button[normalize-space(.)='List']");
    private final By orderIdListView=By.xpath("(//button[.//span[contains(@class,'mdc-button__label')]]//span[contains(@class,'mdc-button__label')])[6]");



    //Actions
    public String getOrderIdListView()
    {
        String actualValue=ElementActions.getText(driver,orderIdListView);
        LogsUtil.info("Actual value: "+actualValue);
        return actualValue;
    }
    public ListViewPage clickListView() {
        LogsUtil.info("Clicking on List View Button");
        ElementActions.clickElement(driver,listView);
        return this;
    }

    public ListViewPage NavigateToListView () {
        LogsUtil.info("Navigating to List View Page");
        BrowserActions.navigateToURL(driver, getPropertyValue("viewListURL"));
        return this;
    }
    public ListViewPage validateorderId(String popupOrderId, String listViewOrderId, String message) {
        LogsUtil.info("Validating order ID...");
        Validations.validateEquals(
                popupOrderId.replace("#", "").trim(),
                listViewOrderId.trim(),
                message
        );
        LogsUtil.info("✅ Order ID matched");
        return this;
    }


}
