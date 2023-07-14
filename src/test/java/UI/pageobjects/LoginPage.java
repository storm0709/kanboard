package UI.pageobjects;

import UI.pageobjects.header.HeaderSection;
import UI.pageobjects.projectmanagement.ProjectSummaryPage;
import UI.pageobjects.tasks.BoardPage;
import UI.pageobjects.tasks.TaskSummaryPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class LoginPage {
    public final SelenideElement userNameField = $x("//*[@id=\"form-username\"]");
    public final SelenideElement passwordField = $x("//*[@id='form-password']");
    public final SelenideElement signInBtn = $x("//*[@type='submit']");
    public final SelenideElement credsErrorAlert = $(".alert");

    @Step("Open the Login page")
    public LoginPage openLoginPage(){
        Selenide.open("");
        return new LoginPage();
    }

//    @Step("Open the User Dashboard page")
//    public LoginPage openUserDashboardPage(){
//        Selenide.open("/");
//        return new LoginPage();
//    }
    @Step("User logs in the app with login {0} and password {1}")
    public HeaderSection loginByUser(String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return new HeaderSection();
    }
    @Step("User logs in the app")
    public ProjectSummaryPage login(String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }
    @Step("User logs in the app")
    public BoardPage loginTask(String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return new BoardPage();
    }
    @Step("User logs in the app")
    public TaskSummaryPage loginTaskSummary(String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return new TaskSummaryPage();
    }
}
