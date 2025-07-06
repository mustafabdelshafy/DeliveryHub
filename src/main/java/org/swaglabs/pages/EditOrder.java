package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.swaglabs.utils.*;

import java.time.Duration;

public class EditOrder {
    // Variables
    private WebDriver driver;
    private ListViewPage listViewPage=new ListViewPage(driver);
    private String environment;

    // Constructor
    public EditOrder(WebDriver driver) {
        this.driver = driver;
        this.environment = PropertiesUtils.getPropertyValue("env"); // أو استخدم system property
    }

//Locators
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
    public EditOrder editPickupTask(String customerName, String customerPhone) {
        LogsUtil.info("Editing the pickup task form");
        ElementActions.clickElement(driver, pickup);

        ElementActions.sendData(driver, customerName1, customerName);
        ElementActions.sendData(driver, customerPhone1, customerPhone);

        ElementActions.clickElement(driver, governorate1);
        ElementActions.clickElement(driver, selectGovernorate);

        ElementActions.clickElement(driver, area1);
        ElementActions.clickElement(driver, selectArea);

        ElementActions.clickElement(driver, block1);
        ElementActions.clickElement(driver, selectBlock);

        ElementActions.clickElement(driver, Street1);
        ElementActions.clickElement(driver, selectStreet);

        ElementActions.sendData(driver, building1, "100");
        ElementActions.sendData(driver, floor1, "100");
        ElementActions.sendData(driver, flat1, "155");

        return this;
    }

    public EditOrder editDeliveryTask(String customerName, String customerPhone) {
        LogsUtil.info("Editing the delivery task form");
        ElementActions.clickElement(driver, Delivery);

        // ✅ تجاهل environment وامشي دايمًا على delivery locators
        ElementActions.sendData(driver, customerName2, customerName);
        ElementActions.sendData(driver, customerPhone2, customerPhone);
        ElementActions.clickElement(driver, governorate2);
        ElementActions.clickElement(driver, selectGovernorate);
        ElementActions.clickElement(driver, area2);
        ElementActions.clickElement(driver, selectArea);
        ElementActions.clickElement(driver, block2);
        ElementActions.clickElement(driver, selectBlock);
        ElementActions.clickElement(driver, Street2);
        ElementActions.clickElement(driver, selectStreet);
        ElementActions.sendData(driver, building2, "100");
        ElementActions.sendData(driver, floor2, "100");
        ElementActions.sendData(driver, flat2, "155");

        return this;
    }


    public EditOrder clickUpdateTaskButton()
    {
        LogsUtil.info("Click Update Task Button");
        ElementActions.clickElement(driver, updateTaskButton);
        return this;
    }

}
