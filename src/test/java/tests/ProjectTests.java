package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Click;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.header.HeaderSection;
import pageobjects.projectmanagement.NewProjectPage;
import pageobjects.projectmanagement.ProjectSummaryPage;

import static com.codeborne.selenide.Condition.*;

public class ProjectTests extends BaseTest{
     private final static String USERNAME = "admin";
     private final static String PASSWORD = "admin";
     String projectName = "AutoTest1";
     @Test
     @Step("Checking positive case Create a new project from the Header dropdown")
     public void addNewProjectFromHeaderTest() {
          SelenideElement newProjectTitle = new LoginPage()
                  .logIn(USERNAME, PASSWORD)
                  .addNewProjectHeader()
                  .createNewProjectDuplicated(projectName)
                  .getTitle().shouldBe(visible);
          newProjectTitle.shouldHave(exactText(projectName));

          // +++ ADD checking in DB +++

          // +++ADD Post-condition DELETE the project+++
          ProjectSummaryPage addedProject = new ProjectSummaryPage()
                  .removeProject();
     }


     @Test
     @Step("Checking positive case Cancel creation a new project")
     public void cancelAddNewProjectTest() {
          SelenideElement newProjectTitle = new LoginPage()
                  .logIn(USERNAME, PASSWORD)
                  .addNewProjectHeader()
                  .cancelCreateNewProject(projectName)
                  .getTitle().shouldBe(visible);
          newProjectTitle.shouldHave(text("Dashboard"));
     }

     @Test
     @Step("Checking positive case Remove a project")
     public void removeProjectTest(){

          // +++ADD Pre-condition CREATE the project and MODIFY the Test +++

          SelenideElement newProjectTitle = new LoginPage()
                  .logIn(USERNAME, PASSWORD)
                  .addNewProjectHeader()
                  .createNewProject(projectName)
                  .removeProject()
                  .getTitle().shouldBe(visible);
          newProjectTitle.shouldHave(text("Projects"));

          // +++ ADD checking in DB +++
     }

     @Test
     @Step("Checking positive case Cancel removal a project")
     public void cancelRemoveProjectTest(){

          // +++ADD Pre-condition CREATE the project and MODIFY the Test +++

          SelenideElement newProjectTitle = new LoginPage()
                  .logIn(USERNAME, PASSWORD)
                  .addNewProjectHeader()
                  .createNewProject(projectName)
                  .removeProjectCancel()
                  .getTitle().shouldBe(visible);
          newProjectTitle.shouldHave(exactText(projectName));

          // +++ ADD checking in DB +++

          // +++ADD Post-condition DELETE the project+++
          ProjectSummaryPage addedProject = new ProjectSummaryPage()
                  .removeProject();
     }
}
