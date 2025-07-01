package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.swaglabs.utils.*;

public class P03_DashboardPage {
    private WebDriver driver;

    public P03_DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By sideMenuIcon = By.xpath("//button[.//mat-icon[text()='menu']]");
    private final By dashboardOption = By.xpath("//a[span[text()='Merchant Dashboard']]");
    private final By calendarIcon = By.cssSelector("app-merchant-dashboard button[aria-label='Choose Date'].p-datepicker-trigger");
    private final By dropdown = By.xpath("//mat-select[@role='combobox']");
    private final By selectYearOption = By.xpath("//mat-option[.//span[normalize-space()='Year']]");
    private final By selectMonthOption = By.xpath("//mat-option[.//span[contains(text(),'Month')]]");
    private final By selectDayOption = By.xpath("//mat-option[.//span[normalize-space()='Day']]");

    // Actions
    public P03_DashboardPage openDashboard() {
        ElementActions.clickElement(driver, sideMenuIcon);
        ElementActions.clickElement(driver, dashboardOption);
        return waitUntilDashboardPageReady(); // ✅ استدعاء طريقة الانتظار هنا
    }

    public P03_DashboardPage navigateToDashboard() {
        LogsUtil.info("Navigating to dashboard page");
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("dashboardURL"));
        return waitUntilDashboardPageReady(); // ✅ استدعاء طريقة الانتظار هنا كمان
    }

    // ✅ الطريقة الموحدة للانتظار على تحميل صفحة الـ Dashboard
    public P03_DashboardPage waitUntilDashboardPageReady() {
        LogsUtil.info("Waiting until Dashboard Page is fully loaded...");
        Waits.waitForElementVisible(driver, dropdown); // أو عنصر مميز آخر في الصفحة
        return this;
    }


    public P03_DashboardPage openCalendar() {
        ElementActions.clickElement(driver, calendarIcon);
        return this;
    }

    public P03_DashboardPage selectCalendarYear(String year) {
        openCalendarDropdown();
        ElementActions.clickElement(driver, selectYearOption);
        openCalendar();
        By yearLocator = By.xpath(String.format("//span[contains(@class, 'p-yearpicker-year') and normalize-space()='%s']", year));
        ElementActions.clickElement(driver, yearLocator);
        return this;
    }

    public P03_DashboardPage selectCalendarMonth(String month) {
        openCalendarDropdown();
        ElementActions.clickElement(driver, selectMonthOption);
        openCalendar();
        By monthLocator = By.xpath(String.format("//span[contains(@class, 'p-monthpicker-month') and normalize-space()='%s']", month));
        ElementActions.clickElement(driver, monthLocator);
        return this;
    }
    public P03_DashboardPage selectCalendarDay() {
        openCalendarDropdown();
        ElementActions.clickElement(driver, selectDayOption);
        openCalendar();
        By dayLocator = By.cssSelector("td.p-datepicker-today span.p-highlight");
        ElementActions.clickElement(driver, dayLocator);
        return this;
    }

    public P03_DashboardPage selectCalendarDay(String dayLabel) {
        openCalendarDropdown();
        ElementActions.clickElement(driver, selectDayOption);
        openCalendar();
        String xpath = String.format("//td[@aria-label='%s']/span[contains(@class, 'p-ripple')]", dayLabel.trim());
        By dayLocator = By.xpath(xpath);
        ElementActions.clickElement(driver, dayLocator);
        return this;
    }

    private P03_DashboardPage openCalendarDropdown() {
        ElementActions.clickElement(driver, dropdown);

        try {
            // نحاول ننتظر عنصر Day Option بعد أول click
            Waits.waitForElementVisible(driver, selectDayOption);
        } catch (TimeoutException e) {
            // لو فشل الظهور، نضغط مرة تانية وننتظر من تاني
            LogsUtil.warn("Dropdown options didn't appear after first click, retrying...");
            ElementActions.clickElement(driver, dropdown);
            Waits.waitForElementVisible(driver, selectDayOption);
        }

        return this;
    }


}
