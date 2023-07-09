package UI.tests;

import UI.pageobjects.header.UserMenuDropdown;
import com.codeborne.selenide.WebDriverRunner;
import UI.dataproviders.LoginDataProvider;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import UI.pageobjects.LoginPage;

public class LoginTests extends BaseTest{
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "admin";
    private final static String URL = "http://localhost/login";

    @Test(dataProvider = "adminCredentials", dataProviderClass = LoginDataProvider.class)
    @Step("Checking positive and negative cases Login as Admin with username {0} and password {1}")
    public void loginTest(String username, String password, String url) {
        new LoginPage()
                .logIn(username, password);
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), url
                , "The logIn was not successful");
    }

    @Test
    @Step("Checking positive case Logout as Admin")
    public void logoutTest() {
        new LoginPage()
                .logIn(USERNAME, PASSWORD);
        new UserMenuDropdown()
                .logOut();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), URL
                , "The logout was not successful");
    }
}
