package API.tests.projects;

import API.POJO.BodyResult;
import API.POJO.bodies.projects.ProjectProperties;
import API.POJO.bodies.users.UserProperties;
import API.POJO.steps.ProjectApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.Randomizer.getRandomInt;

public class ProjectActionsApiTests {
    private static final String PROJECTNAME = "project";
    private String projectId;
    private Integer ownerId=getRandomInt();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME+getRandomInt());
        System.out.println(projectId);
    }

    @Test
    @Step("API test checks negative case of removal a project")
    public void removeProjectNegativeApiTest(){
        Boolean isDeleted = projectApiSteps.removeProject(projectId+projectId);
        Assert.assertFalse(isDeleted.booleanValue(), "Project is removed");
    }

    @Test
    @Step("API test checks positive case of getting a project properties by project id")
    public void getProjectPropertiesTest(){
        BodyResult<ProjectProperties> projectProperties = projectApiSteps.getProjectProperties(Integer.valueOf(projectId));
        projectProperties.getResult().getName().contains(PROJECTNAME);
        Assert.assertNotNull(projectProperties.getResult().getUrl(), "URL is null");
    }

    @Test
    @Step("API test checks negative case of getting a project properties by project id")
    public void getProjectPropertiesNegativeTest(){
        BodyResult<ProjectProperties> projectProperties = projectApiSteps.getProjectProperties(Integer.valueOf(projectId+projectId));
        Assert.assertNull(projectProperties.getResult(), "Returns project properties");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.removeProject(projectId);
    }
}
