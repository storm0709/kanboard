package API.POJO.steps;

import API.POJO.BodyArgs;
import API.POJO.BodyResult;
import API.POJO.bodies.projects.CreateProject;
import API.POJO.bodies.projects.ProjectId;
import API.POJO.bodies.projects.ProjectName;
import API.POJO.bodies.projects.ProjectProperties;
import API.POJO.bodies.users.UpdateUser;
import API.POJO.bodies.users.UserId;
import API.POJO.bodies.users.UserProperties;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static API.POJO.methods.Projects.*;
import static API.POJO.methods.Users.GET_USER;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class ProjectApiSteps extends BaseApiSteps{
    public String createProjectRequiredParam(String projectName){
        CreateProject args = CreateProject.builder()
                .name(projectName)
                .build();
        return createProjectBody(args);
    }

    public String createProjectNoRequiredParam(Integer ownerId){
        CreateProject args = CreateProject.builder()
                .owner_id(ownerId)
                .build();
        return createProjectBody(args);
    }

    private String createProjectBody(CreateProject args){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public boolean removeProject(String projectId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectId(Integer.valueOf(projectId)))
                .method(REMOVE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public boolean addProjectUser(String projectId, String userId, String projectRole){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(List.of(projectId, userId, projectRole))
                .method(ADD_PROJECT_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public BodyResult<ProjectProperties> getProjectProperties (Integer projectId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectId(projectId))
                .method(GET_PROJECT_BY_ID)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return response.as(new TypeRef<BodyResult<ProjectProperties>>() {});
    }
    public BodyResult<ProjectProperties> getProjectPropertiesByName (String projectName){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectName(projectName))
                .method(GET_PROJECT_BY_NAME)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return response.as(new TypeRef<BodyResult<ProjectProperties>>() {});
    }
}
