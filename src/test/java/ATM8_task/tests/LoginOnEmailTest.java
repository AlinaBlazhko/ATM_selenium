package ATM8_task.tests;

import ATM8_task.bo.EmailContent;
import ATM8_task.bo.User;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class LoginOnEmailTest {


    @Test(description = "perform login email")
    public void login(){
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open("https://mail.yandex.ru/");
        $(By.cssSelector(".HeadBanner-ButtonsWrapper a.HeadBanner-Button-Enter")).click();
        $(By.name("login")).val(User.getUSER()).pressTab();
        $(By.name("passwd")).val(User.getPASSWORD()).pressEnter();
        $(By.xpath("//div[text()='alinaBlazhko']")).shouldBe(visible);
    }

    @Test(description = "write email", dependsOnMethods = "login")
    public void writeEmailAndSafeAsDraft(){
//        private static final String SUBJECT = "Email for test";
//        private static final String BODY = "Hello Mr. Smith!";
//        private By to = By.cssSelector("div.js-compose-field.mail-Bubbles");
//        private By subject = By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev");
//        private By letter = By.cssSelector("textarea.cke_editable_themed.cke_contents_ltr");
//        private By closeButton = By.xpath("//div[@title='Закрыть']");
//        private By sentButton = By.xpath("//span[text() = 'Отправить']");
        $(By.cssSelector("span.mail-ComposeButton-Text")).click();
        $(By.cssSelector("div.js-compose-field.mail-Bubbles")).val(EmailContent.getRECIPIENT()).pressTab();
        $(By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field." +
                "js-editor-tabfocus-prev")).val(EmailContent.getSUBJECT()).pressTab();
        $(By.cssSelector("textarea.cke_editable_themed.cke_contents_ltr")).val(EmailContent.getBODY());

    }
}
