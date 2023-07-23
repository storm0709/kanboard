package API.tests.projects;
import API.POJO.steps.ProjectApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static utils.Randomizer.getRandomInt;
public class CreateProjectNegativeApiTests {
    private String projectId;
    private Integer ownerId=getRandomInt();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();

    @Test
    @Step("API test checks negative case of creation a project - without the required parameter Project name")
    public void createProjectApiNegativeTest(){
        projectId = projectApiSteps.createProjectNoRequiredParam(ownerId);
        Assert.assertFalse(Boolean.parseBoolean(projectId), "Project is created");
    }
}
