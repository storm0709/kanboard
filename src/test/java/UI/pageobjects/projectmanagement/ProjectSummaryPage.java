package UI.pageobjects.projectmanagement;

import UI.pageobjects.header.HeaderSection;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class ProjectSummaryPage extends HeaderSection {
    private final SelenideElement removeItem = $x("//a[contains(text(), 'Remove')]");
    private final SelenideElement removeProjectModalWindow = $("#modal-box");
    private final SelenideElement yesBtn = $("#modal-confirm-button");
    private final SelenideElement cancelBtn = $(".form-actions>a");
    private final SelenideElement backlogOpenTasks = $x("//td[contains(text(),'Backlog')]/following-sibling::td[3]");

    @Step("User clicks on Remove menu item, gets confirmation modal window and clicks on Yes button")
    public ProjectSummaryPage removeProject(){
        getRemoveItem().shouldBe(Condition.visible).click();
        getRemoveProjectModalWindow().shouldBe(Condition.visible);
        getYesBtn().shouldBe(Condition.visible).click();
        return this;
    }

    @Step("User clicks on Remove menu item, gets confirmation modal window and clicks on Cancel button")
    public ProjectSummaryPage removeProjectCancel(){
        getRemoveItem().shouldBe(Condition.visible).click();
        getCancelBtn().shouldBe(Condition.visible).click();
        return this;
    }

    public SelenideElement getCancelBtn() {
        return cancelBtn;
    }

    public SelenideElement getYesBtn() {
        return yesBtn;
    }

    public SelenideElement getRemoveProjectModalWindow() {
        return removeProjectModalWindow;
    }

    public SelenideElement getRemoveItem() {
        return removeItem;
    }

    public SelenideElement getBacklogOpenTasks() {
        return backlogOpenTasks;
    }
}
