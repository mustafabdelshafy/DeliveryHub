package com.swagles.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.swaglabs.drivers.DriverManager;
import org.swaglabs.listeners.TestNGListeners;
import org.swaglabs.pages.HomePage;
import org.swaglabs.pages.LoginPage;
import org.swaglabs.utils.*;
import org.testng.annotations.*;

import static org.swaglabs.utils.PropertiesUtils.getPropertyValue;

@Listeners(TestNGListeners.class)
public class E2e {
    // variables

   WebDriver driver;

    JsonUtils testData;

    // Configurations
    @Description("This Test case verify that the user logged in successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void successfulLogin()
    {
        new LoginPage(driver).enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton().assertSuccessfulLoginSoft();
    }

    @Test(dependsOnMethods = "successfulLogin")
    public void addingProductToCart()
    {
        new HomePage(driver).addSpecificProductToCart(testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"));
    }

    @BeforeClass
    public  void beforeClass()
    {
        testData=new JsonUtils("test-data");
        String browserName=getPropertyValue("browserType");
        driver= DriverManager.createInstance(browserName);
        new LoginPage(driver).navigateToLoginPage();
    }
   /* @BeforeMethod
    public void setUp()
    {

    }*/

    //Tests


    @AfterMethod
    public void tearDown()
    {
      /*  BrowserActions.closeBrowser(driver);
        CustomSoftAssertion.customAssertAll();*/
    }
}
