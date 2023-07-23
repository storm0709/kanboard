package UI.tests.login;

import UI.pageobjects.header.HeaderSection;
import UI.pageobjects.header.UserMenuDropdown;
import UI.tests.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import UI.dataproviders.LoginDataProvider;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import UI.pageobjects.LoginPage;
import static utils.EnvProperties.BASE_URL;

public class LoginTests extends BaseTest {
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "admin";
    private final static String URL = BASE_URL+"/login";

    @Test(dataProvider = "adminCredentials", dataProviderClass = LoginDataProvider.class)
    @Step("Checking positive and negative cases Login as Admin with username {0} and password {1}")
    public void loginTest(String username, String password, String url) {
        new LoginPage()
                .openLoginPage()
                .loginGeneric(HeaderSection.class, username, password);
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), url
                , "The logIn was not successful");
    }

    @Test
    @Step("Checking positive case Login as Admin then Logout")
    public void logoutTest() {
        new LoginPage()
                .openLoginPage()
                .loginGeneric(HeaderSection.class, USERNAME, PASSWORD);
        new UserMenuDropdown()
                .logOut();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), URL
                , "The logout was not successful");
    }
}
