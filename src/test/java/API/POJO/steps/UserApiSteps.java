package API.POJO.steps;

import API.POJO.BodyArgs;
import API.POJO.BodyResult;
import API.POJO.bodies.users.CreateUser;
import API.POJO.bodies.users.UpdateUser;
import API.POJO.bodies.users.UserId;
import API.POJO.bodies.users.UserProperties;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import static API.POJO.enums.UserRoles.ADMIN;
import static API.POJO.enums.UserRoles.USER;
import static API.POJO.methods.Users.*;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class UserApiSteps extends BaseApiSteps{
    public String createUser(String username, String password){

        CreateUser args = CreateUser.builder()
                .username(username)
                .password(password)
                .role(ADMIN.getRole())
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
                response.then().statusCode(200);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public boolean removeUser(String userId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new UserId(Integer.valueOf(userId)))
                .method(REMOVE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
                response.then().statusCode(200);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public BodyResult<UserProperties> getUserInfo(String userId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new UserId(Integer.valueOf(userId)))
                .method(GET_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
                 response.then().statusCode(200);
        return response.as(new TypeRef<BodyResult<UserProperties>>() {});
    }

    public boolean updateUserRoleRequiredParam(String userId, String userRole){
        UpdateUser args = UpdateUser.builder()
                .id(Integer.valueOf(userId))
                .role(userRole)
                .build();
        return createUserBody(args);
    }

    public boolean updateUserRoleNoRequiredParam(String userRole){
        UpdateUser args = UpdateUser.builder()
                .role(userRole)
                .build();
        return createUserBody(args);
    }

    private boolean createUserBody(UpdateUser args){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(UPDATE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(BodyResult.class).getResult();
    }
}
