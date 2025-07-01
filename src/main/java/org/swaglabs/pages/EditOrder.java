package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.swaglabs.utils.*;

import java.time.Duration;

public class EditOrder {
// Constructor
private String environment;

    public EditOrder(WebDriver driver) {
        this.driver = driver;
        this.environment = PropertiesUtils.getPropertyValue("env"); // أو استخدم system property
    }
    // Variables
    private WebDriver driver;
    private ListViewPage listViewPage=new ListViewPage(driver);

    JsonUtils testData = new JsonUtils("test-data");
    private final By pickup=By.xpath("//mat-expansion-panel-header[contains(., 'Pick-up')]");
    private final By Delivery=By.xpath("//mat-panel-title[contains(., 'Delivery')]");
    private final By customerName1=By.xpath("(//input[@formcontrolname='customerId'])[1]");
    private final By customerName2=By.xpath("(//input[@formcontrolname='customerId'])[2]");
    private final By customerPhone1=By.xpath("(//input[@formcontrolname='phone'])[1]");
    private final By customerPhone2=By.xpath("(//input[@formcontrolname='phone'])[2]");
    private final By governorate1=By.xpath("(//mat-select[@name='governorate'])[1]");
    private final By governorate2=By.xpath("(//mat-select[@name='governorate'])[2]");
    private final By selectGovernorate=By.xpath("//mat-option[.//span[text()=' Hawalli Governorate ']]");
    private final By area1=By.xpath("(//mat-select[@name='area'])[1]");
    private final By area2=By.xpath("(//mat-select[@name='area'])[2]");
    private final By selectArea=By.xpath("(//mat-option)[2]");
    private final By block1 =By.xpath("(//mat-select[@name='block'])[1]");
    private final By block2 =By.xpath("(//mat-select[@name='block'])[2]");
    private final By selectBlock=By.xpath("(//mat-option)[2]");
    private final By Street1=By.xpath("(//mat-select[@name='street'])[1]");
    private final By Street2=By.xpath("(//mat-select[@name='street'])[2]");
    private final By selectStreet=By.xpath("(//mat-option)[3]");
    private final By building1=By.xpath("(//input[@name='building'])[1]");
    private final By building2=By.xpath("(//input[@name='building'])[2]");
    private final By floor1=By.xpath("(//input[@name='floor'])[1]");
    private final By floor2=By.xpath("(//input[@name='floor'])[2]");
    private final By flat1=By.xpath("(//input[@name='flat'])[1]");
    private final By flat2=By.xpath("(//input[@name='flat'])[2]");
    private final By openUnassignedTask=By.xpath("(//p[contains(@class, 'Unassigned-text')])[1]");
    private final By moreOptionsIcon = By.xpath("//mat-icon[normalize-space(text())='more_vert']");
    private final By editOrderButton = By.xpath("//button[.//span[normalize-space(text())='Edit Order']]");
    private final String urlTemplate = PropertiesUtils.getPropertyValue("editOrderUrl");
    private final By updateTaskButton=By.xpath("//button[normalize-space(.)='Update Task']");


    // Actions
    public EditOrder navigateToHomePage()
    {
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }

    public EditOrder openUnassignedTask()
    {
        LogsUtil.info("Open Unassigned Task");
        ElementActions.clickElement(driver, openUnassignedTask);
        return this;
    }
    public EditOrder clickMoreOptionsIcon()
    {
        LogsUtil.info("Click More Options Icon");
        ElementActions.clickElement(driver, moreOptionsIcon);
        return this;
    }
    public EditOrder clickEditOrderButton() {
        LogsUtil.info("Click Edit Order Button");
        ElementActions.clickElement(driver, editOrderButton);
        //String orderID = new ListViewPage(driver).getOrderId();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("/edit-order/"));
        // B) أو عنصر داخل الصفحة:
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-expansion-panel-header[contains(., 'Pick-up')]")));

        return this;
    }

    public EditOrder navigateToEditOrderPage(String orderId) {
        LogsUtil.info("Navigate to Edit Order Page with ID: " + orderId);
        String finalUrl = urlTemplate.replace("${orderId}", orderId);
        BrowserActions.navigateToURL(driver, finalUrl);
        return this;
    }
    // Method to fill address info for any task form (pickup/delivery/edit)
    private void fillAddressInfo(By customerName1, String customerName,
                                 By customerPhone1, String customerPhone,
                                 By governorate1, By area1, By block1, By street1,
                                 By building1, By floor1, By flat1) {

        LogsUtil.info("Filling address information for task");

        ElementActions.sendData(driver, customerName1, customerName);
        ElementActions.sendData(driver, customerPhone1, customerPhone);

        ElementActions.clickElement(driver, governorate1);
        ElementActions.clickElement(driver, selectGovernorate);

        ElementActions.clickElement(driver, area1);
        ElementActions.clickElement(driver, selectArea);

        ElementActions.clickElement(driver, block1);
        ElementActions.clickElement(driver, selectBlock);

        ElementActions.clickElement(driver, street1);
        ElementActions.clickElement(driver, selectStreet);

        ElementActions.sendData(driver, building1, "100");
        ElementActions.sendData(driver, floor1, "100");
        ElementActions.sendData(driver, flat1, "155");
    }
    public EditOrder editPickupTask(String customerName, String customerPhone) {
        LogsUtil.info("Editing the pickup task form");
        ElementActions.clickElement(driver, pickup);

        fillAddressInfo(
                customerName1, customerName,
                customerPhone1, customerPhone,
                governorate1, area1, block1, Street1,
                building1, floor1, flat1
        );

        return this;
    }


    public EditOrder editDeliveryTask(String customerName, String customerPhone) {
        LogsUtil.info("Editing the delivery task form");
        ElementActions.clickElement(driver, Delivery);

        if (environment.equalsIgnoreCase("staging")) {
            LogsUtil.info("Running in staging: Using pickup locators for delivery task");

            fillAddressInfo(
                    customerName1, customerName,
                    customerPhone1, customerPhone,
                    governorate1, area1, block1, Street1,
                    building1, floor1, flat1
            );
        } else {
            LogsUtil.info("Running in test: Using actual delivery locators");

            fillAddressInfo(
                    customerName2, customerName,
                    customerPhone2, customerPhone,
                    governorate2, area2, block2, Street2,
                    building2, floor2, flat2
            );
        }

        return this;
    }

    public EditOrder clickUpdateTaskButton()
    {
        LogsUtil.info("Click Update Task Button");
        ElementActions.clickElement(driver, updateTaskButton);
        return this;
    }

}
