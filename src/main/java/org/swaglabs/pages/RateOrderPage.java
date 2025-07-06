package org.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RateOrderPage {

    private WebDriver driver;

   public RateOrderPage(WebDriver driver) {
       this.driver = driver;
   }

   // Locators

    final By orderRatingDetails = By.xpath("//div[@role='tab' and contains(., 'Order Rating Details')]");


   // Methods
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
