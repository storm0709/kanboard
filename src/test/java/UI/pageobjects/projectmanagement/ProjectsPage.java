package UI.pageobjects.projectmanagement;

import UI.pageobjects.LoginPage;
import UI.pageobjects.header.HeaderSection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class ProjectsPage extends HeaderSection {
    @Step("Open Projects page")
    public LoginPage openProjectSummaryPage(){
        Selenide.open("/projects");
        return new LoginPage();
    }
}
