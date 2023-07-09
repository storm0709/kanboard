package API.tests;

import API.POJO.BodyResult;
import API.POJO.bodies.users.UserId;
import API.POJO.steps.UserApiSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTests {
    @Test
    public void createUserApiTest(){
        UserApiSteps userApiSteps = new UserApiSteps();
//        BodyResult<UserId> userIdResult = userApiSteps.createUser("TestUser", "123456");
        String userIdResult = userApiSteps.createUser("TestUser", "123456");
        Assert.assertNotNull(userIdResult, "User is not created");
        System.out.println(userIdResult);
    }
}
