package ATM8_task.tests;

import ATM8_task.bo.EmailContent;
import ATM8_task.bo.User;
import ATM8_task.po.*;
import ATM8_task.util.MethodsForTests;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;


public class LoginOnEmailTest {

    private MainPage mainPage = page(MainPage.class);
    private LoginPage loginPage = page(LoginPage.class);
    private Header header = page(Header.class);
    private EmailPage emailPage = page(EmailPage.class);
    private EmailPopup emailPopup = page(EmailPopup.class);
    private LeftSection leftSection = page(LeftSection.class);
    private CenterPart centerPart = page(CenterPart.class);

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
        sleep(5000);
        Assert.assertTrue(title().contains("Яндекс.Почта")); ;
    }

    @Test(description = "write email", dependsOnMethods = "login")
    public void writeEmailAndSafeAsDraft(){
        header.openNewEmail();
        emailPage.writeEmail(EmailContent.getRECIPIENT(), EmailContent.getSUBJECT(), EmailContent.getBODY());
        emailPage.closeEmail();
        emailPopup.closeEmailAndSaveAsDraft();
        leftSection.openDraftFolder();
        MethodsForTests.refreshPage();
        Assert.assertTrue(centerPart.countOfDrafts() == 1);
    }

    @AfterTest(alwaysRun = true)
    public void after(){
        $("span.checkbox_view").setSelected(true);
        $(By.xpath("//span[text()='Удалить']")).click();
    }
}
