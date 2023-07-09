package UI.pageobjects.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import UI.pageobjects.projectmanagement.NewProjectPage;

import static com.codeborne.selenide.Selenide.$;

public class HeaderSection {
    private final SelenideElement logo = $(".logo");
    private final SelenideElement title = $(".title");
    private final SelenideElement notificationBtn = $(".notification");
    private final SelenideElement addProjectBtn = $("[class='fa fa-plus fa-fw']");
    private final SelenideElement userMenuBtn = $(".avatar-letter");

    @Step("User clicks on Add ptoject dropdown")
    public NewProjectPage addNewProjectHeader(){
        getAddProjectBtn().shouldBe(Condition.visible).click();
        NewProjectPage selectOpt = new ProjectMenuDropdown()
                .clickAddNewProject();
        return new NewProjectPage();
    }

    public SelenideElement getLogo() {
        return logo;
    }

    public SelenideElement getTitle() {
        return title;
    }

    public SelenideElement getNotificationBtn() {
        return notificationBtn;
    }

    public SelenideElement getAddProjectBtn() {

        return addProjectBtn;
    }

    public SelenideElement getUserMenuBtn() {
        return userMenuBtn;
    }
}
