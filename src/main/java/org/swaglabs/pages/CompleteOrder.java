package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.swaglabs.utils.*;

import java.util.List;
import java.util.NoSuchElementException;

public class CompleteOrder {
    private WebDriver driver;

    // Constructor
    public CompleteOrder(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By clickOnAssignedTab=By.xpath("(//div[@role='tab'][.//span[contains(text(),'Assigned')]])[2]");
    private final By assignedTask= By.cssSelector("a.task-status.Assigned");
    private final By moreOptionsIcon = By.xpath("//mat-icon[normalize-space(text())='more_vert']");
    private final By changeStatusButton = By.xpath("//button[normalize-space(.)='Change task status']");
    private final By statusDropdown=By.xpath("(//div[contains(@class, 'mat-mdc-select-trigger')])[2]");
    private final By selectSuccessfulStatus=By.xpath("//mat-option[.//span[normalize-space(.)='Successful']]");
    private final By checkConnectedTasks=By.cssSelector("mat-checkbox[formcontrolname='isChangeConnectedTasks'] input[type='checkbox']");
    private final By submitButton=By.xpath("//button[.//span[normalize-space(.)='Submit']]");
    private final By iconCloseTask=By.xpath("//div[@mattooltip='close task details']//mat-icon[normalize-space(.)='close']");
    private final By clickOnCompleteTab=By.xpath("//span[@class='mdc-tab__text-label' and contains(normalize-space(.), 'Completed')]");
   // private final By completedId=By.xpath("//li[@class='task-info']//h5[starts-with(normalize-space(.), '#')]");
    // Actions
    public CompleteOrder navigateToHomePage() {
        LogsUtil.info("Navigating to Home Page");
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }

    public CompleteOrder clickOnAssignedTab() {
        LogsUtil.info("Clicking on Assigned Tab");
        ElementActions.clickElement(driver, clickOnAssignedTab);
        return this;
    }
    public CompleteOrder clickOnAssignedTask() {
        LogsUtil.info("Clicking on Assigned Task");
        ElementActions.clickElement(driver, assignedTask);
        return this;
    }
    public CompleteOrder clickMoreOptionsIcon() {
        LogsUtil.info("Click More Options Icon");
        ElementActions.clickElement(driver, moreOptionsIcon);
        return this;
    }
    public CompleteOrder clickChangeStatusButton() {
        LogsUtil.info("Click Change Status Button");
        ElementActions.clickElement(driver, changeStatusButton);
        return this;
    }
    public CompleteOrder clickStatusDropdown() {
        LogsUtil.info("Click Status Dropdown");
        ElementActions.clickElement(driver, statusDropdown);
        return this;
    }
    public CompleteOrder selectSuccessfulStatus() {
        LogsUtil.info("Click Select Successful Status");
        ElementActions.clickElement(driver, selectSuccessfulStatus);
        return this;
    }
    public CompleteOrder clickToConnectedTasks() {
        LogsUtil.info("Click To Connected Tasks");
        ElementActions.forceClickElement(driver, checkConnectedTasks);
        return this;
    }
    public CompleteOrder clickSubmitButton() {
        LogsUtil.info("Click Submit Button");
        ElementActions.clickElement(driver, submitButton);
        return this;
    }
    public CompleteOrder closeTheTask() {
        LogsUtil.info("Closing the Task After Change Status");
        ElementActions.clickElement(driver,iconCloseTask);
        return this;
    }
    public CompleteOrder clickOnCompleteTab() {
        LogsUtil.info("Clicking on Complete Tab");
        ElementActions.clickElement(driver, clickOnCompleteTab);
        return this;
    }

    public static boolean isTaskIdPresent(WebDriver driver, String expectedTaskId) {
        String formattedTaskId = expectedTaskId.trim();
        if (!formattedTaskId.startsWith("#")) {
            formattedTaskId = "#" + formattedTaskId;
        }

        try {
            driver.findElement(By.xpath("//li[@class='task-info']//h5[normalize-space(text())='" + formattedTaskId + "']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public RateOrderPage validateOrderCompletion() {
        LogsUtil.info("Checking that the order exists in the complete tasks");
        String taskId = RuntimeData.get("createdOrderId");; // أو تجيبه من الـ RuntimeData

        boolean found = isTaskIdPresent(driver, taskId);
        Validations.validateTrue(found, "Task ID not found in the list: " + taskId);
      return new RateOrderPage(driver);
    }






}
