package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.swaglabs.utils.ElementActions;


public class ReportsPage {
    private WebDriver driver;

    // Locators
    private final By sideMenuIcon = By.xpath("/html/body/app-root/app-full-layout/div/mat-toolbar[1]/div[1]/button/span[3]");
    private final By reportsMainOption = By.xpath("//*[@id=\"scroll-top\"]/app-menu-list-item[5]/div/a");
    private final By subReportsOption = By.xpath("//*[@id=\"scroll-top\"]/app-menu-list-item[5]/div/div/div[2]/a");

    // Constructor
    public ReportsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public ReportsPage openReportsSection() {
        ElementActions.clickElement(driver, sideMenuIcon);
       // Waits.(1000); // Wait for animation or load
        ElementActions.clickElement(driver, reportsMainOption);
       // Waits.sleep(1000); // Wait for submenu to appear
        ElementActions.clickElement(driver, subReportsOption);
        return this;
    }
}
