package pageobjects.tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pageobjects.projectmanagement.ProjectSummaryPage;

import static com.codeborne.selenide.Selenide.$;

public class NewTaskPage {

    private final SelenideElement taskTitle = $("#form-title");
    private final SelenideElement saveBtn = $("button[type=submit]");

    @Step("User fills in Task name field and click on Save button")
    public ProjectSummaryPage createNewTaskFromDropdown(String taskName){
        getTaskTitle().shouldBe(Condition.visible).sendKeys(taskName);
        getSaveBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }

    public SelenideElement getTaskTitle() {
        return taskTitle;
    }

    public SelenideElement getSaveBtn() {
        return saveBtn;
    }
}
