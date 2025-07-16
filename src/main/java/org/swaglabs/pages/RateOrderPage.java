package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.swaglabs.utils.ElementActions;
import org.swaglabs.utils.LogsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RateOrderPage {

    private WebDriver driver;

   public RateOrderPage(WebDriver driver) {
       this.driver = driver;
   }

   // Locators
   private final By sideMenuIcon = By.xpath("//button[.//mat-icon[text()='menu']]");
   private final By settings = By.xpath("//a[.//span[text()='Settings']]");
   private final By logs=By.xpath("//a[normalize-space(text())='Logs']");
   private final By reportsSection = By.xpath("//a[.//span[text()='Reports']]");
   private final By merchantOrderOption = By.xpath("//a[text()='Merchant Orders Report']");
   private final By eyeDetails =By.xpath("(//mat-icon[normalize-space(text())='remove_red_eye'])[1]");
   private final By textOfMessage=By.xpath("//td[contains(@class, 'cdk-column-smsBody')]");
   private final By orderRatingDetails = By.xpath("//div[@role='tab' and contains(., 'Order Rating Details')]");
   private final By star =By.xpath("(//app-star-rating//button[contains(@class, 'mat-mdc-icon-button')])[3]");
   private final By submitButton =By.xpath("//button[.//span[text()='Submit'] or .//span[text()='إرسال']]");
   private final By successMSG=By.xpath("//h3[@class='rate-txt' and normalize-space()='Successfully Done']");

   // Methods

    public RateOrderPage clickOnSideMenue()
    {
        LogsUtil.info("Clicking on side Menu ");
        ElementActions.clickElement(driver,sideMenuIcon);
        return this;
    }
    public RateOrderPage clickOnSettings()
    {
        LogsUtil.info("Clicking on Settings ");
        ElementActions.clickElement(driver,settings);
        return this;
    }
    public RateOrderPage clickOnLogs()
    {
        LogsUtil.info("Clicking on Logs ");
        ElementActions.clickElement(driver,logs);
        return this;
    }
    public RateOrderPage clickOnReports()
    {
        LogsUtil.info("Clicking on Reports ");
        ElementActions.clickElement(driver,reportsSection);
        return this;
    }
    public RateOrderPage clickOnMerchantOrderOption()
    {
        LogsUtil.info("Clicking on Merchant Order Option ");
        ElementActions.clickElement(driver,merchantOrderOption);
        return this;
    }
    public RateOrderPage clickOnEyeDetails()
    {
        LogsUtil.info("Clicking on Eye Details ");
        ElementActions.clickElement(driver,eyeDetails);
        return this;
    }
    public String getRateURL()
    {
        LogsUtil.info("Get the Rating URL");
        return  ElementActions.getText(driver,textOfMessage);
    }
    public RateOrderPage openRateURL()
    {
        Pattern pattern = Pattern.compile("https://[\\w./#=?%-]+");
        Matcher matcher = pattern.matcher(getRateURL());

        String ratingLink = null;
        if (matcher.find()) {
            ratingLink = matcher.group(); // دا هو اللينك اللي طلع
        }

        if (ratingLink != null) {
            ((JavascriptExecutor) driver).
                    executeScript("window.open(arguments[0], '_blank');", ratingLink);
        }
        return this;
    }

    public RateOrderPage clickOnStar(int starNumber) {
        LogsUtil.info("Clicking on Star #" + starNumber);

        List<WebElement> stars = driver.findElements(
                By.xpath("//app-star-rating//button[contains(@class, 'mat-mdc-icon-button')]")
        );

        WebElement star = stars.get(starNumber - 1); // 1-based index
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", star);
        star.click();

        return this;
    }

    public RateOrderPage switchToNewTab() {
        LogsUtil.info("Switching to new tab");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        return this;
    }

    public RateOrderPage clickOnSubmitButton()
 {
     LogsUtil.info("Clicking on Submit Button");
     ElementActions.clickElement(driver,submitButton);
     return this;
 }
 public RateOrderPage checkSuccessMessage()
 {

 }

}
