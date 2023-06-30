package pageobjects.dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pageobjects.header.HeaderSection;
import pageobjects.projectmanagement.ProjectSummaryPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardOverviewPage extends HeaderSection {
    private SelenideElement projectConfigurationDropdown = $x("//strong[contains(text(),'102')]");
    private final SelenideElement configureThisProjectMenuItem = $(".dropdown-submenu-open li:nth-last-child(1)");

    @Step("User selects a project from the list")
    public ProjectSummaryPage selectConfigureThisProject(){
        getProjectConfigurationDropdown().shouldBe(Condition.visible).click();
        getConfigureThisProjectMenuItem().shouldBe(Condition.visible).click();
        return new ProjectSummaryPage();
    }

    public SelenideElement getProjectConfigurationDropdown() {
        return projectConfigurationDropdown;
    }

    public SelenideElement getConfigureThisProjectMenuItem() {
        return configureThisProjectMenuItem;
    }
}
