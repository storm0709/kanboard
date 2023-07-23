package API.tests.tasks;

import API.POJO.BodyResult;
import API.POJO.bodies.tasks.TaskProperties;
import API.POJO.steps.ProjectApiSteps;
import API.POJO.steps.TaskApiSteps;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.Randomizer.getRandomInt;

public class TaskActionsApiTests {
    private static final String PROJECTNAME = "project";
    private static final String TASK_TITLE = "task";
    private String projectId;
    private String taskId;
    BodyResult<TaskProperties> taskProperties;
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApiSteps.createProjectRequiredParam(PROJECTNAME+getRandomInt());
        taskId = taskApiSteps.createTaskRequiredParams(TASK_TITLE+getRandomInt(), Integer.valueOf(projectId));
    }

    @Test
    @Step("API test checks positive case of getting a task")
    public void getTaskPropertiesApiTest(){
        taskProperties = taskApiSteps.getTaskProperties(Integer.valueOf(taskId));
        taskProperties.getResult().getTitle().contains(TASK_TITLE);
        Assert.assertNotNull(taskProperties.getResult().getUrl(), "URL is null");
    }

    @Test
    @Step("API test checks negative case of getting a task")
    public void getTaskPropertiesApiNegativeTest(){
        taskProperties = taskApiSteps.getTaskProperties(Integer.valueOf(taskId+taskId));
        Assert.assertNull(taskProperties.getResult(), "Task properties are returned");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        taskApiSteps.removeTask(Integer.valueOf(taskId));
        projectApiSteps.removeProject(projectId);
    }
}
