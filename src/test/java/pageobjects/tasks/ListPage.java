package pageobjects.tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pageobjects.projectmanagement.projectheader.ProjectHeaderSection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ListPage extends ProjectHeaderSection {
    private SelenideElement taskConfigurationDropdown = $x("//strong[contains(text(),'28')]");
    private final SelenideElement removeMenuItem = $(".dropdown-submenu-open li:nth-last-child(2)");
    private SelenideElement taskName = $x("//a[contains(text(),'Task1')]");
    private final SelenideElement tasksAlert = $(".alert");

    @Step("User clicks on the Task name")
    public TaskSummaryPage clickOnTaskName(){
        getTaskName().shouldBe(Condition.visible).click();
        return new TaskSummaryPage();
    }

    public SelenideElement getTasksAlert() {
        return tasksAlert;
    }

    public SelenideElement getTaskConfigurationDropdown() {
        return taskConfigurationDropdown;
    }

    public SelenideElement getRemoveMenuItem() {
        return removeMenuItem;
    }

    public SelenideElement getTaskName() {
        return taskName;
    }
}

