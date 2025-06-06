package org.swaglabs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.swaglabs.utils.BrowserActions;
import org.swaglabs.utils.CustomSoftAssertion;
import org.swaglabs.utils.ElementActions;
import org.swaglabs.utils.Validations;
import org.testng.Assert;
import static org.swaglabs.utils.PropertiesUtils.getPropertyValue;

public class LoginPage {

    //variables
    private WebDriver driver;
    //Locators
<<<<<<< HEAD
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
=======
    private final By email = By.cssSelector("input[formcontrolname='email']");
    private final By password = By.cssSelector("input[formcontrolname='password']");
    private final By signInButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector("div.mat-mdc-snack-bar-label.mdc-snackbar__label");
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1

    //constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to the login Page
    @Step("Navigate to the Login Page ")
    public LoginPage navigateToLoginPage() {

<<<<<<< HEAD
        BrowserActions.navigateToURL(driver, "https://www.saucedemo.com/");
=======
        BrowserActions.navigateToURL(driver, getPropertyValue("baseURL"));
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
        return this;
    }


    //Actions
    @Step("Enter Username: {0}")
<<<<<<< HEAD
    public LoginPage enterUserName(String username) {

        ElementActions.sendData(driver, this.username, username);
=======
    public LoginPage enterUserName(String email) {

        ElementActions.sendData(driver, this.email, email);
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
        return this;
    }

    @Step("Enter Password: {0}")
    public LoginPage enterPassword(String password) {

        ElementActions.sendData(driver, this.password, password);
        return this;
    }
@Step("Click Login button")
    public LoginPage clickLoginButton() {
<<<<<<< HEAD
        ElementActions.clickElement(driver, loginButton);
        return this;
    }
@Step("Get Error Message")
=======
        ElementActions.clickElement(driver, signInButton);
        return this;
    }
    /**
     * Retrieves the error message text from the error message element
     * @return error message text
     */
    @Step("Get Error Message")
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
    public String getErrorMessage() {
        return ElementActions.getText(driver, errorMessage);
    }

<<<<<<< HEAD

    //Validations
    @Step("Assert Login Page URL")
    public LoginPage assertAfterLoginPageUrl() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentURL(driver), getPropertyValue("homeURL"),
                "The URL is Right");
        return this;
    }
    @Step("Assert Login Page Title")
=======
    //Validations
    @Step("Assert Login Page URL")
    public LoginPage assertAfterLoginPageUrl() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentURL(driver), getPropertyValue("baseURL"),
                "The URL is Right");
        CustomSoftAssertion.softAssertion.assertAll();

        return this;
    }

   /* @Step("Assert Login Page Title")
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
    public LoginPage assertLoginPageTitle() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getPageTitle(driver), getPropertyValue("appTitle"),
                "Title is not as Expected");
        return this;
<<<<<<< HEAD
    }

    public LoginPage assertSuccessfulLoginSoft() {
        assertAfterLoginPageUrl().assertLoginPageTitle();
=======
    }*/

    public LoginPage assertSuccessfulLoginSoft() {
        assertAfterLoginPageUrl();
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
        return this;
    }
    @Step("Assert Successful Login")
    public LoginPage assertSuccessfullogin() {
<<<<<<< HEAD
        Validations.validatePageUrl(driver, getPropertyValue("homeURL"));
=======
        Validations.validatePageUrl(driver, getPropertyValue("baseURL"));
>>>>>>> 3c3056b1224ce36c5c629bbf90c2802d11a8b4e1
        return this;
    }
    @Step("Assert UNSuccessful Login")
    public LoginPage assertUnsuccessfullLogin() {
        Validations.validateEquals(getErrorMessage(), getPropertyValue("errorMSG"),
                "the error message is not the same");
        return this;
    }


}
