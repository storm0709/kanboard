package pageobjects.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.projectmanagement.NewProjectPage;
import tests.BaseTest;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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
