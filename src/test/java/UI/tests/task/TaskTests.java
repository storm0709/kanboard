package UI.tests.task;

import API.POJO.steps.ProjectApiSteps;
import API.POJO.steps.TaskApiSteps;
import API.POJO.steps.UserApiSteps;
import UI.pageobjects.tasks.TaskSummaryPage;
import UI.tests.BaseTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static API.POJO.enums.UserRoles.ADMIN;
import static utils.Randomizer.getRandomInt;

public class TaskTests extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "123456";
    private String userId;
    private String username;
    private Boolean isRoleUpdated;
    private String projectId;
    private String projectName = "AutoTest";
    private String taskName = "Task"+getRandomInt();
    private String commentText = "This is the comment "+getRandomInt();
    private String taskId;
    UserApiSteps userApiSteps = new UserApiSteps();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();
    TaskApiSteps taskApiSteps = new TaskApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME+getRandomInt(),PASSWORD);
        isRoleUpdated = userApiSteps.updateUserRoleRequiredParam(userId, ADMIN.getRole());
        username = userApiSteps.getUserInfo(userId).getResult().getUsername();
        projectId = projectApiSteps.createProjectRequiredParam(projectName+getRandomInt());
        taskId=taskApiSteps.createTaskRequiredParams(taskName+getRandomInt(), Integer.valueOf(projectId));
    }

    @Test
    @Step("Checking case Remove a task")
    public void removeTaskTest(){
        SelenideElement addedTask = new TaskSummaryPage()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginTaskSummary(username, PASSWORD)
                .clickRemoveItem()
                .taskId(taskId);
        addedTask.should(Condition.disappear);

//         +++ ADD checking in DB +++

    }


    @Test
    @Step("Checking case Close this task")
    public void closeTaskTest(){
        SelenideElement taskStatus = new TaskSummaryPage()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginTaskSummary(username, PASSWORD)
                .clickCloseThisTask()
                .taskStatusClosed().shouldBe(Condition.visible);
        taskStatus.shouldHave(Condition.text("close"));
    }

    @Test
    @Step("Checking case Add the first comment to the task")
    public void addFirstCommentTest(){
        SelenideElement comment = new TaskSummaryPage()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginTaskSummary(username, PASSWORD)
                .clickAddCommentItem()
                .createFirstComment(commentText)
                .commentText(commentText).shouldBe(Condition.visible);
        comment.shouldHave(Condition.exactText(commentText));


//         +++ ADD checking in DB +++

    }


    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        taskApiSteps.removeTask(Integer.valueOf(taskId));
        projectApiSteps.removeProject(projectId);
        userApiSteps.removeUser(userId);
    }
}
