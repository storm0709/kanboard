package UI.tests.project;

import API.POJO.steps.ProjectApiSteps;
import API.POJO.steps.UserApiSteps;
import UI.pageobjects.LoginPage;
import UI.pageobjects.dashboard.DashboardOverviewPage;
import UI.pageobjects.header.HeaderSection;
import UI.pageobjects.projectmanagement.ProjectSummaryPage;
import UI.tests.BaseTest;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static utils.EnvProperties.BASE_URL;
import static utils.Randomizer.getRandomInt;
import static API.POJO.enums.UserRoles.ADMIN;

public class CreateNewProjectTests extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "123456";
    private String userId;
    private String username;
    private Boolean isRoleUpdated;
    private String projectName = "AutoTest"+getRandomInt();
    UserApiSteps userApiSteps = new UserApiSteps();
    ProjectApiSteps projectApiSteps = new ProjectApiSteps();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME+getRandomInt(),PASSWORD);
        isRoleUpdated = userApiSteps.updateUserRoleRequiredParam(userId, ADMIN.getRole());
        username = userApiSteps.getUserInfo(userId).getResult().getUsername();
    }

    @Test
    @Step("Checking positive case Create a new project from the Header dropdown")
    public void addNewProjectFromHeaderTest() {
        SelenideElement newProjectTitle = new DashboardOverviewPage()
                .openUserDashboardPage()
                .loginByUser(username, PASSWORD)
                .addNewProjectHeader()
                .createNewProject(projectName)
                .getTitle().shouldBe(visible);
        newProjectTitle.shouldHave(exactText(projectName));

        // +++ ADD checking in DB +++


    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApiSteps.removeProject(projectApiSteps.getProjectPropertiesByName(projectName).getResult().getId());
        userApiSteps.removeUser(userId);
    }
}
