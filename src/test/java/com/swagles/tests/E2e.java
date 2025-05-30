package com.swagles.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.swaglabs.drivers.DriverManager;
<<<<<<< HEAD
=======
import org.swaglabs.drivers.GUIDriver;
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
import org.swaglabs.listeners.TestNGListeners;
import org.swaglabs.pages.HomePage;
import org.swaglabs.pages.LoginPage;
import org.swaglabs.utils.*;
import org.testng.annotations.*;

import static org.swaglabs.utils.PropertiesUtils.getPropertyValue;

@Listeners(TestNGListeners.class)
public class E2e {
    // variables

<<<<<<< HEAD
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
=======
    private JsonUtils testData;
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        testData = new JsonUtils("test-data");
        new  GUIDriver(getPropertyValue("browserType"));
        driver = GUIDriver.getInstance();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, description = "Verify that user can login with valid credentials")
    public void loginTest() {
        loginPage
                .navigateToLoginPage()
                .enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfulLoginSoft();

    }

    @Test(priority = 2, dependsOnMethods = "loginTest", description = "Verify that user can create a pickup and delivery order")
    public void createOrder() {
        String customerName1 = testData.getJsonData("customer-names.user1.name");
        String customerPhone1 = testData.getJsonData("customer-names.user1.phoneNumber");
        String customerName2 = testData.getJsonData("customer-names.user2.name");
        String customerPhone2 = testData.getJsonData("customer-names.user2.phoneNumber");
        String filePath = getPropertyValue("filePath");

        homePage
                .navigateToHomePage()
                .clickingCreateOrder()
                .fillPickupTask(customerName1, customerPhone1, filePath)
                .fillDeliveryTask(customerName2, customerPhone2, filePath)
                .clickCreateTaskButton().checkAccountName();
    }

    // Clean up:
    // 1. Renamed method and variables to follow Java conventions
    // 2. Removed debugging statements
    // 3. Improved readability by breaking up long lines
    // 4. Removed redundant comments
    // 5. Used consistent naming conventions for variables
    // 6. Removed duplicated code by reusing the same variables for both pickup and delivery tasks


   /*   @AfterMethod
    public void tearDown() {
      BrowserActions.closeBrowser(driver);
        CustomSoftAssertion.customAssertAll();
    }*/
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
}
