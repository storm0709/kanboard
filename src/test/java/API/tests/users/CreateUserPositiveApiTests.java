package API.tests.users;

import API.POJO.steps.UserApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static utils.Randomizer.getRandomInt;

public class CreateUserPositiveApiTests {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "123456";
    UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;
    @Test
    @Step("API test checks positive case of creation a user")
    public void createUserApiTest(){
        userId = userApiSteps.createUser(USERNAME+getRandomInt(), PASSWORD);
        Assert.assertNotEquals(userId, false, "User is not created");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        userApiSteps.removeUser(userId);
    }

}
