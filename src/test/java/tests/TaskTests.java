package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.dashboard.DashboardOverviewPage;
import pageobjects.projectmanagement.ProjectSummaryPage;
import pageobjects.projectmanagement.projectheader.ProjectHeaderSection;
import pageobjects.tasks.BoardPage;

public class TaskTests extends BaseTest{
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "admin";
    String taskName = "Task1";
    String url = new BoardPage().getBoardUrl();

    @BeforeTest
    @Step("Preconditions set up")
    public void runPreConditions() {
     // +++ADD Pre-condition CREATE the project+task and MODIFY the Test +++


        Selenide.open("http://localhost/login");
        new LoginPage().logIn(USERNAME, PASSWORD);
        new DashboardOverviewPage().selectConfigureThisProject();
    }
    @Test
    @Step("Checking positive case Create a new task from the dropdown")
    public void addNewTaskFromDropdownTest(){
         SelenideElement openTasks = new ProjectHeaderSection()
                 .addNewTaskDropdown()
                 .createNewTaskFromDropdown(taskName)
                 .getBacklogOpenTasks().shouldBe(Condition.visible);
         openTasks.shouldHave(Condition.exactText("1"));

        // +++ ADD checking in DB +++

        // +++ADD Post-condition DELETE the project and the task+++
    }

//    @BeforeTest
//    @Step("Preconditions set up")
//    public void runPreConditions1() {
//        // +++ADD Pre-condition CREATE the project+task and MODIFY the Test +++
//
//        Selenide.open("http://localhost/login");
//        new LoginPage().logIn(USERNAME, PASSWORD);
//        new DashboardOverviewPage().selectConfigureThisProject();
//
//        new ProjectHeaderSection()
//                .addNewTaskDropdown()
//                .createNewTaskFromDropdown(taskName);
//    }
    @Test
    @Step("Checking positive case Remove a task")
    public void removeTaskTest(){
        SelenideElement alertMessage = new ProjectHeaderSection()
                .navigateToListPage()
                .clickOnTaskName()
                .clickRemoveItem()
                .getTasksAlert();

        alertMessage.shouldHave(Condition.exactText("No tasks found."));

        // +++ ADD checking in DB +++

    }

//    @BeforeTest
//    @Step("Preconditions set up")
//    public void runPreConditions2() {
//        // +++ADD Pre-condition CREATE the project+task and MODIFY the Test +++
//
//
//        Selenide.open("http://localhost/login");
//        new LoginPage().logIn(USERNAME, PASSWORD);
//        new DashboardOverviewPage().selectConfigureThisProject();
//
//        new ProjectHeaderSection()
//                .addNewTaskDropdown()
//                .createNewTaskFromDropdown(taskName);
//    }
    @Test
    @Step("Checking positive case Close this task")
    public void closeTaskTest(){
        SelenideElement taskStatus = new ProjectHeaderSection()
                .navigateToListPage()
                .clickOnTaskName()
                .clickCloseThisTask()
                .getTaskStatusClosed().shouldBe(Condition.visible);
        taskStatus.shouldHave(Condition.text("close"));
    }

    // +++ ADD checking in DB +++

    // +++ADD Post-condition DELETE the project and task+++

}
