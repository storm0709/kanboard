package UI.pageobjects;

import UI.pageobjects.header.HeaderSection;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    public final SelenideElement userNameField = $x("//*[@id=\"form-username\"]");
    public final SelenideElement passwordField = $x("//*[@id='form-password']");
    public final SelenideElement signInBtn = $x("//*[@type='submit']");
    public final SelenideElement credsErrorAlert = $(".alert");

    @Step("User logs in the app with login {0} and password {1}")
    public HeaderSection logIn(String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return new HeaderSection();
    }

    public SelenideElement getUserNameField() {
        return userNameField;
    }

    public SelenideElement getPasswordField() {
        return passwordField;
    }

    public SelenideElement getSignInBtn() {
        return signInBtn;
    }

    public SelenideElement getCredsErrorAlert() {
        return credsErrorAlert.shouldBe(Condition.visible);
    }
}
