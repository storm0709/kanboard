package pageobjects.tasks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects.projectmanagement.projectheader.ProjectHeaderSection;

import static com.codeborne.selenide.Selenide.$$x;

public class BoardPage extends ProjectHeaderSection {
    private String boardUrl = "http://localhost/board/102";


    public String getBoardUrl() {
        return boardUrl;
    }
}
