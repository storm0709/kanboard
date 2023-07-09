package UI.pageobjects.tasks;

import UI.pageobjects.projectmanagement.projectheader.ProjectHeaderSection;

public class BoardPage extends ProjectHeaderSection {
    private String boardUrl = "http://localhost/board/102";


    public String getBoardUrl() {
        return boardUrl;
    }
}
