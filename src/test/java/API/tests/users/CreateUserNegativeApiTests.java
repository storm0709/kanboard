package API.tests.users;
import API.POJO.dataprovidersapi.NegativeUserCreds;
import API.POJO.steps.UserApiSteps;
import UI.dataproviders.LoginDataProvider;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static utils.Randomizer.getRandomInt;

public class CreateUserNegativeApiTests {
    UserApiSteps userApiSteps = new UserApiSteps();
    private String userIdResult;

    @Test(dataProvider = "negativeUserCredentials", dataProviderClass = NegativeUserCreds.class)
    @Step("API test checks negative cases of creation a user with username {0} and password {1}")
    public void createUserApiTestNeg(String username, String password){
        userIdResult = userApiSteps.createUser(username,password);
        Assert.assertFalse(Boolean.parseBoolean(userIdResult), "User is created");
        System.out.println(userIdResult);
    }
}
