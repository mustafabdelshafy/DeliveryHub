package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.swaglabs.utils.*;

public class HomePage {
    WebDriver driver;
    JsonUtils testData;

    // Locators
    private final By createOrderButton = By.cssSelector("button.btn-create-task");
    private final By pickup = By.xpath("//mat-expansion-panel-header[contains(., 'Pick-up')]");
    private final By Delivery = By.xpath("//mat-panel-title[contains(., 'Delivery')]");
    private final By customerName1 = By.xpath("(//input[@formcontrolname='customerId'])[1]");
    private final By customerName2 = By.xpath("(//input[@formcontrolname='customerId'])[2]");
    private final By customerPhone1 = By.xpath("(//input[@formcontrolname='phone'])[1]");
    private final By customerPhone2 = By.xpath("(//input[@formcontrolname='phone'])[2]");
    private final By governorate1 = By.xpath("(//mat-select[@name='governorate'])[1]");
    private final By governorate2 = By.xpath("(//mat-select[@name='governorate'])[2]");
    private final By selectGovernorate = By.xpath("//mat-option[.//span[text()=' Asma Governorate ']]");
    private final By area1 = By.xpath("(//mat-select[@name='area'])[1]");
    private final By area2 = By.xpath("(//mat-select[@name='area'])[2]");
    private final By selectArea = By.xpath("(//mat-option)[2]");
    private final By block1 = By.xpath("(//mat-select[@name='block'])[1]");
    private final By block2 = By.xpath("(//mat-select[@name='block'])[2]");
    private final By selectBlock = By.xpath("(//mat-option)[3]");
    private final By Street1 = By.xpath("(//mat-select[@name='street'])[1]");
    private final By Street2 = By.xpath("(//mat-select[@name='street'])[2]");
    private final By selectStreet = By.xpath("(//mat-option)[3]");
    private final By building1 = By.xpath("(//input[@name='building'])[1]");
    private final By building2 = By.xpath("(//input[@name='building'])[2]");
    private final By floor1 = By.xpath("(//input[@name='floor'])[1]");
    private final By floor2 = By.xpath("(//input[@name='floor'])[2]");
    private final By flat1 = By.xpath("(//input[@name='flat'])[1]");
    private final By flat2 = By.xpath("(//input[@name='flat'])[2]");
    private final By chooseFile1 = By.xpath("(//button[.//span[text()='Choose File']])[1]");
    private final By chooseFile2 = By.xpath("(//button[.//span[text()='Choose File']])[2]");
    private final By fileInput = By.id("file-inputpickup0");
    private final By fileInput2 = By.id("file-inputdelivery0");
    private final By createTaskButton = By.cssSelector("button.create-task-btn");
    private final By AccountName = By.xpath("(//span[contains(text(),'Account 1')])[2]");
    private final By orderId = By.xpath("(//li[contains(@class, 'order-id')])[1]");
    private final By tripRadioButtonLabel = By.xpath("//mat-radio-button[@value='customer']");
    private final By branchRadioButtonLabel = By.xpath("//mat-radio-button[@value='branch']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage navigateToHomePage() {
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }

    public HomePage clickingCreateOrder() {
        LogsUtil.info("Clicking on Create order");
        LogsUtil.info("Before waiting for Create button");
        Waits.waitForElementClickable(driver, createOrderButton);
        LogsUtil.info("Create button is clickable");
        ElementActions.clickElement(driver, createOrderButton);
        return this;
    }
    public  HomePage openPickupTask() {
        LogsUtil.info("Opening the pickup task");
        ElementActions.clickElement(driver, pickup);
        return this;
    }
    public HomePage roundTrip() {
        LogsUtil.info("Selecting Round Trip");
        ElementActions.clickElement(driver, tripRadioButtonLabel);
        return this;
    }

    public HomePage fillPickupTask(String customerName, String customerPhone, String filePath) {
        LogsUtil.info("Filling the pickup task form");
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
        ElementActions.sendData(driver, building1, "1");
        ElementActions.sendData(driver, floor1, "1");
        ElementActions.sendData(driver, flat1, "1");
        ElementActions.uploadFile(driver, fileInput, filePath);
        return this;
    }

    public HomePage openDeliveryTask() {
        LogsUtil.info("Opening the delivery task");
        ElementActions.clickElement(driver, Delivery);
        return this;
    }
    public HomePage fillDeliveryTask(String customerName, String customerPhone, String filePath, boolean usePickupLocators) {
        LogsUtil.info("Filling the delivery task form");

        if (usePickupLocators) {
            fillDeliveryLikePickup(customerName, customerPhone, filePath);
        } else {
            fillDeliveryNormally(customerName, customerPhone, filePath);
        }

        return this;
    }

    private void fillDeliveryLikePickup(String customerName, String customerPhone, String filePath) {
        // ⬅️ استخدم Locators بتوع Pickup
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
        ElementActions.sendData(driver, building1, "1");
        ElementActions.sendData(driver, floor1, "1");
        ElementActions.sendData(driver, flat1, "1");
        ElementActions.uploadFile(driver, fileInput, filePath); // pickup file input
    }

    private void fillDeliveryNormally(String customerName, String customerPhone, String filePath) {
        // ⬅️ استخدم Locators العادية
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
        ElementActions.sendData(driver, building2, "1");
        ElementActions.sendData(driver, floor2, "1");
        ElementActions.sendData(driver, flat2, "1");
        ElementActions.uploadFile(driver, fileInput2, filePath); // delivery file input
    }


    public HomePage clickCreateTaskButton() {
        LogsUtil.info("Clicking on Creating Task Button");
        ElementActions.clickElement(driver, createTaskButton);
        return this;
    }

    public String getOrderId() {
        // ✨ انتظر ظهور العنصر فعليًا لضمان تحديثه
        Waits.waitForElementVisible(driver, orderId);
        String actualValue = ElementActions.getText(driver, orderId);
        LogsUtil.info("Actual value: " + actualValue);
        return actualValue;
    }


    public String getExistingOrderIdOrNull() {
        try {
            if (driver.findElements(orderId).isEmpty()) {
                LogsUtil.info("📭 Order ID element not found on the page.");
                return null;
            }
            String id = driver.findElement(orderId).getText().replace("#", "").trim();
            LogsUtil.info("📦 Found existing Order ID: " + id);
            return id;
        } catch (Exception e) {
            LogsUtil.warn("⚠️ Exception while reading existing Order ID: " + e.getMessage());
            return null;
        }
    }

    public void waitForOrderIdToChange(String oldOrderId) {
        if (oldOrderId == null || oldOrderId.isEmpty()) {
            LogsUtil.info("🕒 Waiting for order ID to appear...");
            Waits.waitForElementVisible(driver, orderId); // أول ظهور
        } else {
            LogsUtil.info("🕒 Waiting for order ID to change from: " + oldOrderId);
            Waits.waitForNewTextToAppear(driver, orderId, "#" + oldOrderId);
        }
    }


    public HomePage checkAccountName() {
        String actualValue = ElementActions.getText(driver, AccountName);
        LogsUtil.info("Actual value: " + actualValue);
        Validations.validateEquals(actualValue, "Account 1", "Account name not matched");
        LogsUtil.info("Account name matched");
        return this;
    }
}
