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
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    //constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to the login Page
    @Step("Navigate to the Login Page ")
    public LoginPage navigateToLoginPage() {

        BrowserActions.navigateToURL(driver, "https://www.saucedemo.com/");
        return this;
    }


    //Actions
    @Step("Enter Username: {0}")
    public LoginPage enterUserName(String username) {

        ElementActions.sendData(driver, this.username, username);
        return this;
    }

    @Step("Enter Password: {0}")
    public LoginPage enterPassword(String password) {

        ElementActions.sendData(driver, this.password, password);
        return this;
    }
@Step("Click Login button")
    public LoginPage clickLoginButton() {
        ElementActions.clickElement(driver, loginButton);
        return this;
    }
@Step("Get Error Message")
    public String getErrorMessage() {
        return ElementActions.getText(driver, errorMessage);
    }


    //Validations
    @Step("Assert Login Page URL")
    public LoginPage assertAfterLoginPageUrl() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentURL(driver), getPropertyValue("homeURL"),
                "The URL is Right");
        return this;
    }
    @Step("Assert Login Page Title")
    public LoginPage assertLoginPageTitle() {
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getPageTitle(driver), getPropertyValue("appTitle"),
                "Title is not as Expected");
        return this;
    }

    public LoginPage assertSuccessfulLoginSoft() {
        assertAfterLoginPageUrl().assertLoginPageTitle();
        return this;
    }
    @Step("Assert Successful Login")
    public LoginPage assertSuccessfullogin() {
        Validations.validatePageUrl(driver, getPropertyValue("homeURL"));
        return this;
    }
    @Step("Assert UNSuccessful Login")
    public LoginPage assertUnsuccessfullLogin() {
        Validations.validateEquals(getErrorMessage(), getPropertyValue("errorMSG"),
                "the error message is not the same");
        return this;
    }


}
