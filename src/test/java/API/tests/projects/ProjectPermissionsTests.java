package API.tests.projects;
import API.POJO.steps.ProjectApiSteps;
import API.POJO.steps.UserApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static API.POJO.enums.ProjectRoles.MANAGER;
import static utils.Randomizer.getRandomInt;

public class ProjectPermissionsTests {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "123456";
    private static final String PROJECTNAME = "project";
    private String projectId;
    private String userId;
    UserApiSteps userApiSteps = new UserApiSteps();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME+getRandomInt(),PASSWORD);
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME+getRandomInt());
    }

    @Test
    @Step("API test checks positive case of adding a project user")
    public void addProjectUserApiTest(){
        Boolean isAdded = projectApiSteps.addProjectUser(projectId, userId, MANAGER.getRoleProject());
        Assert.assertTrue(isAdded, "Project user is not added");
    }

    @Test
    @Step("API test checks negative case of adding a project user")
    public void addProjectUserApiNegativeTest(){
        Boolean isAdded = projectApiSteps.addProjectUser(projectId+projectId, userId, MANAGER.getRoleProject());
        Assert.assertFalse(isAdded, "Project user is added");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.removeProject(projectId);
        userApiSteps.removeUser(userId);
    }
}
