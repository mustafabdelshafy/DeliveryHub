package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.swaglabs.utils.BrowserActions;
import org.swaglabs.utils.ElementActions;
import org.swaglabs.utils.LogsUtil;
import org.swaglabs.utils.PropertiesUtils;

import java.util.List;

public class AssignOrder {
private WebDriver driver;

// Constructor
    public AssignOrder(WebDriver driver) {
        this.driver = driver;
    }


    //Locators

    private final By openUnassignedTask=By.xpath("(//p[contains(@class, 'Unassigned-text')])[1]");
    private final By assignOrderButton = By.xpath("//button[span[text()=' Assign Driver ']]");
    private final By teamDropdown=By.xpath("(//div[starts-with(@id,'mat-select-value-')])[2]");
    private final By moreOptionsIcon = By.xpath("//mat-icon[normalize-space(text())='more_vert']");
    private final By selectTeam=By.xpath("(//mat-option)[1]");
    private final By driverDropdown=By.cssSelector("mat-select[formcontrolname='driverIds']");
    private final By selectDriver=By.xpath("(//mat-option[not(@aria-disabled='true')])[1]");
    private final By submitButton=By.xpath("//button[.//span[normalize-space()='Submit']]");


    //Actions

    public AssignOrder navigateToHomePage() {
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }
    public AssignOrder openUnassignedTask() {
        LogsUtil.info("Open Unassigned Task");
        ElementActions.clickElement(driver, openUnassignedTask);
        return this;
    }
    public AssignOrder clickMoreOptionsIcon()
    {
        LogsUtil.info("Click More Options Icon");
        ElementActions.clickElement(driver, moreOptionsIcon);
        return this;
    }
    public AssignOrder clickAssignOrderButton() {
        LogsUtil.info("Clicking on Assign order Button");
        ElementActions.clickElement(driver,assignOrderButton);
        return this;
    }

    public AssignOrder clickOnTeamDropdown() {
        LogsUtil.info("Selecting Team");
        ElementActions.clickElement(driver, teamDropdown);
        return this;
    }
    public AssignOrder clickSelectTeam() {
        LogsUtil.info("Clicking on Select Team");
        ElementActions.clickElement(driver, selectTeam);
        return this;
    }
    public AssignOrder clckOnDriverDropdown() {
        LogsUtil.info("Selecting Driver");
        ElementActions.clickElement(driver, driverDropdown);
        return this;
    }
    public AssignOrder clickSelectDriver() {
        LogsUtil.info("Clicking on Select Driver");
        ElementActions.clickElement(driver, selectDriver);
        return this;
    }
    public AssignOrder clickOnSubmitButton() {
        LogsUtil.info("Clicking on Submit Button");
        ElementActions.clickElement(driver, submitButton);
        return this;
    }

    public class OrderAssertions {

        public static boolean isTaskIdPresent(WebDriver driver, String expectedTaskId) {
            List<WebElement> allTaskIds = driver.findElements(
                    By.xpath("//ul[@class='date-list']//h5[contains(text(), '#')]")
            );

            return allTaskIds.stream()
                    .map(WebElement::getText)
                    .anyMatch(text -> text.trim().equals(expectedTaskId));
        }
    }








}
