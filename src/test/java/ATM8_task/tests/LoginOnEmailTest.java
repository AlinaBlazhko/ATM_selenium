package ATM8_task.tests;

import ATM8_task.bo.EmailContent;
import ATM8_task.bo.User;
import ATM8_task.po.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;


public class LoginOnEmailTest {

    private MainPage mainPage = page(MainPage.class);
    private LoginPage loginPage = page(LoginPage.class);
    private Header header = page(Header.class);
    private EmailPage emailPage = page(EmailPage.class);
    private EmailPopup emailPopup = page(EmailPopup.class);

    @BeforeTest
    public void setUp(){
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open("https://mail.yandex.ru/");
    }

    @Test(description = "perform login email")
    public void login(){
        mainPage.openLoginPage();
        loginPage.login(User.getUSER(), User.getPASSWORD());

        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(description = "write email", dependsOnMethods = "login")
    public void writeEmailAndSafeAsDraft(){
        header.openNewEmail();
        emailPage.writeEmail(EmailContent.getRECIPIENT(), EmailContent.getSUBJECT(), EmailContent.getBODY());
        emailPage.closeEmail();
        emailPopup.closeEmailAndSaveAsDraft();
    }
}
