package API.tests.users;

import API.POJO.steps.UserApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.Randomizer.getRandomInt;

public class RemoveUserPositiveApiTests {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "123456";
    UserApiSteps userApiSteps = new UserApiSteps();
    private String userId;
    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME+getRandomInt(),PASSWORD);
    }

    @Test
    @Step("API test checks positive case of removal a user")
    public void removeUserApiTest(){
        Boolean isDeleted = userApiSteps.removeUser(userId);
        Assert.assertTrue(isDeleted.booleanValue(), "User is not deleted");
    }
}
