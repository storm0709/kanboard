package UI.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    @Step("User opens a browser window with size 1280x800")
    public void setUp(){
//        String browser = System.getProperty("browser");
//        String headless = System.getProperty("headless");
//        Configuration.browser = browser;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1280x800";
//        if(headless.equals("true")){
//            Configuration.headless = true;
//        }
        Selenide.open("http://localhost/login");
    }

    @AfterMethod
    @Step("User closes a browser")
    public void cleanUp(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}


