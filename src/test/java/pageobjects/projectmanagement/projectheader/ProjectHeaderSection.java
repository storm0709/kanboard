package pageobjects.projectmanagement.projectheader;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pageobjects.tasks.ListPage;
import pageobjects.tasks.NewTaskPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectHeaderSection {
    private final SelenideElement configurationDropdown = $("[class='fa fa-cog']");
    private final SelenideElement addNewTaskMenuItem = $x("//ul[@class='dropdown-submenu-open']//li[1]");
    private final SelenideElement list = $(".view-listing");


    @Step("User clicks on the Configuration dropdown and selects Add New Task option")
    public NewTaskPage addNewTaskDropdown(){
        getConfigurationDropdown().shouldBe(Condition.visible).click();
        getAddNewTaskMenuItem().shouldBe(Condition.visible).click();
        return new NewTaskPage();
    }

    public ListPage navigateToListPage(){
        getList().shouldBe(Condition.visible).click();
        return new ListPage();
    }

    public SelenideElement getConfigurationDropdown() {
        return configurationDropdown;
    }

    public SelenideElement getAddNewTaskMenuItem() {
        return addNewTaskMenuItem;
    }

    public SelenideElement getList() {
        return list;
    }
}
