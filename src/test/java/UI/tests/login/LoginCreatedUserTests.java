package UI.tests.login;

import API.POJO.steps.UserApiSteps;
import UI.dataproviders.LoginDataProvider;
import UI.pageobjects.LoginPage;
import UI.tests.BaseTest;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.EnvProperties.BASE_URL;
import static utils.Randomizer.getRandomInt;

public class LoginCreatedUserTests extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "123456";
    private final static String URL = BASE_URL+"/";
    private String userId;
    private String username;
    UserApiSteps userApiSteps = new UserApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME+getRandomInt(),PASSWORD);
        username = userApiSteps.getUserInfo(userId).getResult().getUsername();
    }

    @Test
    @Step("Checking positive case Login as newly created user")
    public void loginCreatedUserTest() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(username, PASSWORD);
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                            URL, "The logIn was not successful");
    }

    @Test
    @Step("Checking negative case Login as newly created user")
    public void loginCreatedUserNegativeTest() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(username, "");
        Assert.assertNotEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                                URL, "The logIn was successful");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        userApiSteps.removeUser(userId);
    }
}
