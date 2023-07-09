package UI.pageobjects.header;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NotificationModalWindow{
    private final SelenideElement notificationModal = $("#modal-box");

    public SelenideElement getNotificationModal() {
        return notificationModal;
    }
}
