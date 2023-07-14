package UI.tests.project;

import API.POJO.steps.ProjectApiSteps;
import API.POJO.steps.UserApiSteps;
import UI.pageobjects.projectmanagement.ProjectSummaryPage;
import UI.tests.BaseTest;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static API.POJO.enums.UserRoles.ADMIN;
import static com.codeborne.selenide.Condition.*;
import static utils.Randomizer.getRandomInt;

public class RemoveProjectTests extends BaseTest {
     private final static String USERNAME = "user";
     private final static String PASSWORD = "123456";
     private String userId;
     private String username;
     private Boolean isRoleUpdated;
     private String projectId;
     private String projectName = "AutoTest";
     UserApiSteps userApiSteps = new UserApiSteps();
     ProjectApiSteps projectApiSteps = new ProjectApiSteps();

     @BeforeMethod
     @Step("Setup test data")
     public void prepareDataForTest() {
          userId = userApiSteps.createUser(USERNAME+getRandomInt(),PASSWORD);
          isRoleUpdated = userApiSteps.updateUserRoleRequiredParam(userId, ADMIN.getRole());
          username = userApiSteps.getUserInfo(userId).getResult().getUsername();
          projectId = projectApiSteps.createProjectRequiredParam(projectName+getRandomInt());
     }


     @Test
     @Step("Checking positive case Remove a project")
     public void removeProjectTest(){
          SelenideElement newProjectTitle = new ProjectSummaryPage()
                  .openProjectSummaryPage(Integer.valueOf(projectId))
                  .login(username, PASSWORD)
                  .removeProject()
                  .getTitle().shouldBe(visible);
          newProjectTitle.shouldHave(text("Projects"));

          // +++ ADD checking in DB +++
     }


     @AfterMethod(alwaysRun = true)
     @Step("Cleanup test data")
     public void removeDataAfterTest(){
//          projectApiSteps.removeProject(projectApiSteps.getProjectPropertiesByName(projectName).getResult().getId());
          userApiSteps.removeUser(userId);
     }
}
