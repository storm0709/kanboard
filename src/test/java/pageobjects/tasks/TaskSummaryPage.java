package pageobjects.tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pageobjects.projectmanagement.projectheader.ProjectHeaderSection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TaskSummaryPage {
    private final SelenideElement removeItem = $x("//a[text()='Remove']");
    private final SelenideElement closeThisTaskItem = $x("//a[text()='Close this task']");
    private final SelenideElement taskStatusClosed = $x("//span[contains(text(), 'closed')]");
    private final SelenideElement yesBtn = $("#modal-confirm-button");
    private final SelenideElement cancelBtn = $x("//a[text()='cancel']");

    @Step("User clicks on Remove menu item, gets confirmation modal window and clicks on Yes button")
    public ListPage clickRemoveItem(){
        getRemoveItem().shouldBe(Condition.visible).click();
        getYesBtn().shouldBe(Condition.visible).click();
        new ProjectHeaderSection().navigateToListPage();
        return new ListPage();
    }

    @Step("User clicks on Close this task menu item, gets confirmation modal window and clicks on Yes button")
    public TaskSummaryPage clickCloseThisTask(){
        getCloseThisTaskItem().shouldBe(Condition.visible).click();
        getYesBtn().shouldBe(Condition.visible).click();
        return this;
    }

    public SelenideElement getTaskStatusClosed() {
        return taskStatusClosed;
    }

    public SelenideElement getCloseThisTaskItem() {
        return closeThisTaskItem;
    }

    public SelenideElement getRemoveItem() {
        return removeItem;
    }

    public SelenideElement getYesBtn() {
        return yesBtn;
    }

    public SelenideElement getCancelBtn() {
        return cancelBtn;
    }
}
