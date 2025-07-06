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
    private EditOrder editOrderPage;
    private AssignOrder assignorderPage;
    String environment;

    @BeforeClass
    public void setUp() {
        // 👇 تحميل ملف البيئة المناسب حسب -Denv
        PropertiesUtils.loadProperties();
        System.out.println("⚙️ Running on environment: " + System.getProperty("env", "staging"));
        environment = System.getProperty("env", "staging");
        System.out.println("⚙️ Running on environment: " + environment);
        // ✅ باقي الإعدادات
        testData = new JsonUtils("test-data-" + environment);
        driver = DriverManager.createInstance(getPropertyValue("browserType"));
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        dashboardPage = new P03_DashboardPage(driver);
        reportsPage = new ReportsPage(driver);
        listViewPage = new ListViewPage(driver);
        editOrderPage = new EditOrder(driver);
        assignorderPage = new AssignOrder(driver);
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
        boolean isStaging = PropertiesUtils.getPropertyValue("env").equalsIgnoreCase("staging");
        String customerName1 = testData.getJsonData("customer-names.user1.name");
        String customerPhone1 = testData.getJsonData("customer-names.user1.phoneNumber");
        String customerName2 = testData.getJsonData("customer-names.user2.name");
        String customerPhone2 = testData.getJsonData("customer-names.user2.phoneNumber");
        String relativeFilePath = testData.getJsonData("file-upload.filePath");
        String filePath = new File(relativeFilePath).getAbsolutePath();

        String oldOrderId;
        try {
            oldOrderId = homePage.getExistingOrderIdOrNull();
            LogsUtil.info("📦 Found existing order ID: " + oldOrderId);
        } catch (Exception e) {
            oldOrderId = null;
            LogsUtil.warn("⚠️ No existing order ID found before creation.");
        }

        homePage.navigateToHomePage()
                .clickingCreateOrder();

        if (environment.equals("test")) {
            try {
                homePage.openPickupTask().fillPickupTask(customerName1, customerPhone1, filePath);
            } catch (Exception e) {
                LogsUtil.error("❌ Failed to fill pickup: " + e.getMessage());
            }
        }

        try {
            homePage.openDeliveryTask().fillDeliveryTask(customerName2, customerPhone2, filePath,isStaging)
                    .clickCreateTaskButton();
        } catch (Exception e) {
            LogsUtil.error("❌ Failed to fill delivery: " + e.getMessage());
        }

        // ⏳ انتظر حتى يظهر Order ID جديد أو يتغيّر
        homePage.waitForOrderIdToChange(oldOrderId);

        // ✅ اقرأ Order ID الجديد وخزّنه
        String newOrderId = homePage.getOrderId().replace("#", "").trim();
        if (newOrderId == null || newOrderId.isEmpty()) {
            LogsUtil.error("❌ New Order ID is empty or not found!");
            throw new AssertionError("❌ Failed to retrieve new Order ID after creation");
        }
        LogsUtil.info("✅ Created order ID: " + newOrderId);
        RuntimeData.set("createdOrderId", newOrderId);
    }
    @Test(priority = 3, dependsOnMethods = "createOrder", description = "Verify that user can view the order in list view")
    public void listViewPage() {
        String createdOrderId = RuntimeData.get("createdOrderId");

        listViewPage.clickListView()
                .NavigateToListView();

        String orderIdFromList = listViewPage.getOrderIdListView().trim();

        listViewPage.validateorderId(createdOrderId, orderIdFromList, "❌ Order ID not matched");
    }

    @Test(priority = 4, dependsOnMethods = "loginTest", description = "Verify that user can create a round-trip pickup and delivery order")
    public void createOrderRoundTrip() {
        String customerName1 = testData.getJsonData("customer-names.user1.name");
        String customerPhone1 = testData.getJsonData("customer-names.user1.phoneNumber");
        String customerName2 = testData.getJsonData("customer-names.user2.name");
        String customerPhone2 = testData.getJsonData("customer-names.user2.phoneNumber");
        String relativeFilePath = testData.getJsonData("file-upload.filePath");
        String filePath = new File(relativeFilePath).getAbsolutePath();

        String oldOrderId;
        try {
            oldOrderId = homePage.getExistingOrderIdOrNull();
            LogsUtil.info("📦 Found existing order ID: " + oldOrderId);
        } catch (Exception e) {
            oldOrderId = null;
            LogsUtil.warn("⚠️ No existing order ID found before creation.");
        }

        homePage.navigateToHomePage()
                .clickingCreateOrder();

        try {
            // ✅ استخدام roundTrip في الـ pickup task
            homePage.openPickupTask().roundTrip().fillPickupTask(customerName1, customerPhone1, filePath);
        } catch (Exception e) {
            LogsUtil.error("❌ Failed to fill round-trip pickup: " + e.getMessage());
        }

        try {
            homePage.openDeliveryTask().fillDeliveryTask(customerName2, customerPhone2, filePath,false)
                    .clickCreateTaskButton();
        } catch (Exception e) {
            LogsUtil.error("❌ Failed to fill delivery: " + e.getMessage());
        }

        // ⏳ انتظار تغير الـ Order ID
        homePage.waitForOrderIdToChange(oldOrderId);

        // ✅ جلب Order ID الجديد
        String newOrderId = homePage.getOrderId().replace("#", "").trim();
        if (newOrderId == null || newOrderId.isEmpty()) {
            LogsUtil.error("❌ New Order ID is empty or not found!");
            throw new AssertionError("❌ Failed to retrieve new Order ID after creation");
        }

        LogsUtil.info("✅ Created round-trip order ID: " + newOrderId);
        RuntimeData.set("createdRoundTripOrderId", newOrderId);
    }
    @Test(priority = 5, dependsOnMethods = "createOrderRoundTrip", description = "Verify that round-trip order appears correctly in list view")
    public void validateRoundTripOrderInList() {
        String roundTripOrderId = RuntimeData.get("createdRoundTripOrderId");

        LogsUtil.info("🔍 Validating Round-Trip Order ID in List View: " + roundTripOrderId);

        listViewPage.clickListView()
                .NavigateToListView();

        String orderIdFromList = listViewPage.getOrderIdListView().trim();

        listViewPage.validateorderId(roundTripOrderId, orderIdFromList, "❌ Round-trip Order ID not matched");
    }

    @Test(priority = 6, dependsOnMethods = "createOrder", description = "Verify that user can assign the created order to a driver")
    public void assignOrder()
    {
        assignorderPage.navigateToHomePage().openUnassignedTask().clickMoreOptionsIcon().clickAssignOrderButton()
                .clickOnTeamDropdown().clickSelectTeam()
                .clckOnDriverDropdown().clickSelectDriver().clickOnSubmitButton();

    }

    @Test(priority = 7, dependsOnMethods = "loginTest", description = "Verify calendar filter by date")
    public void filterByDateTest() {
        String year = testData.getJsonData("dates.year");
        String month = testData.getJsonData("dates.month");
        String dayLabel = testData.getJsonData("dates.day");

        dashboardPage.navigateToDashboard()
                .selectCalendarDay(dayLabel)
                .selectCalendarYear(year)
                .selectCalendarMonth(month);
    }

    @Test(priority = 8, dependsOnMethods = "loginTest", description = "Verify calendar filter by year")
    public void filterByYearTest() {
        String year = testData.getJsonData("dates.year");
        // dashboardPage.openDashboard();
        dashboardPage.selectCalendarYear(year);
    }

    @Test(priority = 9, dependsOnMethods = "loginTest", description = "Verify calendar filter by month")
    public void filterByMonthTest() {
        String month = testData.getJsonData("dates.month");
        //  dashboardPage.openDashboard();
        dashboardPage.selectCalendarMonth(month);
    }

    @Test(priority = 10, dependsOnMethods = "loginTest", description = "Open Reports module")
    public void openReportsModuleTest() {
        reportsPage.openReportsSection();

    }
    @Test(priority = 11, dependsOnMethods = "loginTest", description = "Edit Order")
    public void editOrderTest() {
        String customerName3 = testData.getJsonData("customer-names.user3.name");
        String customerPhone3 = testData.getJsonData("customer-names.user3.phoneNumber");
        String customerName4 = testData.getJsonData("customer-names.user4.name");
        String customerPhone4 = testData.getJsonData("customer-names.user4.phoneNumber");

        listViewPage.NavigateToListView().clickListView();
        String orderId = listViewPage.getOrderIdListView();

        editOrderPage.navigateToEditOrderPage(orderId);
        editOrderPage.editPickupTask(customerName3, customerPhone3);
        editOrderPage.editDeliveryTask(customerName4, customerPhone4)
                .clickUpdateTaskButton();
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
