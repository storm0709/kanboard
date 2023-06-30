package pageobjects.projectmanagement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pageobjects.dashboard.DashboardOverviewPage;

import static com.codeborne.selenide.Selenide.*;

public class NewProjectPage {
    private final SelenideElement newProjectModal = $("#modal-content");
    private final SelenideElement projectNameField = $x("//*[@id='form-name']");
    private final SelenideElement projectIdentifierField = $x("//*[@id='form-identifier']");
    private final SelenideElement getProjectNameErrorAlert = $(".form-errors");
    private final SelenideElement columnTaskLimitCheckbox = $("//*[@name='per_swimlane_task_limits']");
    private final SelenideElement taskLimitField = $("input[name='task_limit']");
    private final SelenideElement duplicateProjectDropdown = $("option[value='1']");
    private final SelenideElement duplicateFromOption = $x("//option[contains(text(), 'RD')]");
    private final SelenideElement saveBtn = $("button[type='submit']");
    private final SelenideElement cancelBtn = $(".form-actions>a");

    @Step("User creates a new project with a project name")
    public ProjectSummaryPage createNewProject(String projectName){
        getProjectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        getSaveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }
    @Step("User creates a new project with a project name and identifier")
    public ProjectSummaryPage createNewProject(String projectName, String identifier){
        getProjectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        getProjectIdentifierField().shouldBe(Condition.visible).sendKeys(identifier);
        getSaveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }

    @Step("User creates a new project with a project name, identifier and task limit")
    public ProjectSummaryPage createNewProject(String projectName, String identifier, String taskLimit){
        getProjectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        getProjectIdentifierField().shouldBe(Condition.visible).sendKeys(identifier);
        getTaskLimitField().shouldBe(Condition.visible).sendKeys(taskLimit);
        getSaveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }

    @Step("User creates a new project with a project name and duplicating objects from the existing RD project")
    public ProjectSummaryPage createNewProjectDuplicated (String projectName){
        String selected = "selected";
        String value = "true";
        getProjectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        getDuplicateProjectDropdown().shouldBe(Condition.visible);
        getDuplicateFromOption().click();
        getSaveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }

    @Step("User creates a new project with a project name and then clicks on Cancel button")
    public DashboardOverviewPage cancelCreateNewProject(String projectName){
        getProjectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        getCancelBtn().shouldBe(Condition.visible).click();
        return new DashboardOverviewPage();
    }

    public SelenideElement getDuplicateFromOption() {
        return duplicateFromOption;
    }

    public SelenideElement getProjectIdentifierField() {
        return projectIdentifierField;
    }

    public SelenideElement getNewProjectModal() {
        return newProjectModal;
    }

    public SelenideElement getProjectNameField() {
        return projectNameField;
    }

    public SelenideElement getGetProjectNameErrorAlert() {
        return getProjectNameErrorAlert;
    }

    public SelenideElement getColumnTaskLimitCheckbox() {
        return columnTaskLimitCheckbox;
    }

    public SelenideElement getTaskLimitField() {
        return taskLimitField;
    }

    public SelenideElement getDuplicateProjectDropdown() {
        return duplicateProjectDropdown;
    }

    public SelenideElement getSaveBtn() {
        return saveBtn;
    }

    public SelenideElement getCancelBtn() {
        return cancelBtn;
    }
}
