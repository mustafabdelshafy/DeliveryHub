package com.swagles.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.swaglabs.drivers.DriverManager;
import org.swaglabs.listeners.TestNGListeners;
import org.swaglabs.pages.*;
import org.swaglabs.utils.*;
import org.testng.annotations.*;

import java.io.File;

import static org.swaglabs.utils.PropertiesUtils.getPropertyValue;

@Listeners(TestNGListeners.class)
public class E2e {
    // variables

    private JsonUtils testData;
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private P03_DashboardPage dashboardPage;
    private ReportsPage reportsPage;
    private ListViewPage listViewPage;

    @BeforeClass
    public void setUp() {
        testData = new JsonUtils("test-data");
        driver = DriverManager.createInstance(getPropertyValue("browserType"));
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        dashboardPage = new P03_DashboardPage(driver);
        reportsPage=new ReportsPage(driver);
        listViewPage=new ListViewPage(driver);

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
        String relativefilePath = testData.getJsonData("file-upload.filePath");
        String filePath = new File(relativefilePath).getAbsolutePath();

        homePage
                .navigateToHomePage()
                .clickingCreateOrder()
                .fillPickupTask(customerName1, customerPhone1, filePath)
                .fillDeliveryTask(customerName2, customerPhone2, filePath)
                .clickCreateTaskButton();
    }
    @Test(priority = 3, dependsOnMethods = "createOrder", description = "Verify that user can view the order in list view")
    public void listViewPage() {
        String orderIdFromPopup = listViewPage.getOrderId(); // احصل عليه من النافذة بعد الإنشاء

        listViewPage.clickListView()
                .NavigateToListView();

        String orderIdFromList = listViewPage.getOrderIdListView(); // احصل عليه من صفحة الـ List

        listViewPage.validateorderId(orderIdFromPopup, orderIdFromList, "Order id not matched"); // تحقق من التطابق
    }
    @Test(priority = 4, dependsOnMethods = "loginTest", description = "Verify calendar filter by date")
    public void filterByDateTest() {
        String year = testData.getJsonData("dates.year");
        String month = testData.getJsonData("dates.month");
        String dayLabel = testData.getJsonData("dates.day");

        dashboardPage.openDashboard().navigateToDashboard().selectCalendarDay(dayLabel)
                .selectCalendarYear(year)
                .selectCalendarMonth(month);

       /* dashboardPage.selectCalendarYear(year);
        dashboardPage.selectCalendarMonth(month);
        dashboardPage.selectCalendarDay(dayLabel);*/
    }
    @Test(priority = 5, dependsOnMethods = "loginTest", description = "Verify calendar filter by year")
    public void filterByYearTest() {
        String year = testData.getJsonData("dates.year");
        // dashboardPage.openDashboard();
        dashboardPage.selectCalendarYear(year);
    }

    @Test(priority = 6, dependsOnMethods = "loginTest", description = "Verify calendar filter by month")
    public void filterByMonthTest() {
        String month = testData.getJsonData("dates.month");
        //  dashboardPage.openDashboard();
        dashboardPage.selectCalendarMonth(month);
    }

    @Test(priority = 7, dependsOnMethods = "loginTest", description = "Open Reports module")
    public void openReportsModuleTest() {
        reportsPage.openReportsSection();

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
}
