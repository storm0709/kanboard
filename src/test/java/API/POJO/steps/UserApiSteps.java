package API.POJO.steps;

import API.POJO.BodyArgs;
import API.POJO.BodyResult;
import API.POJO.bodies.users.CreateUser;
import API.POJO.bodies.users.UserId;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import static API.POJO.enums.UserRoles.ADMIN;
import static API.POJO.methods.Users.CREATE_USER;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class UserApiSteps extends BaseApiSteps{
//    public BodyResult<UserId> createUser(String username, String password){
    public String createUser(String username, String password){

        CreateUser args = new CreateUser().builder()
                .username(username)
                .password(password)
                .role(ADMIN.getRole())
                .build();

        BodyArgs bodyArgs = new BodyArgs().builder()
                .params(args)
                .method(CREATE_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
                response.then().statusCode(200);
        BodyResult bodyResult = response.as(BodyResult.class);
//        return response.as(new TypeRef<BodyResult<UserId>>(){});
        return bodyResult.getResult().toString();
    }
}
