package API.tests.projects;
import API.POJO.steps.ProjectApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.Randomizer.getRandomInt;
public class RemoveProjectPositiveApiTests {
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
    @Step("API test checks positive case of removal a project")
    public void removeProjectApiTest(){
        Boolean isDeleted = projectApiSteps.removeProject(projectId);
        Assert.assertTrue(isDeleted.booleanValue(), "Project is not deleted");
    }
}
