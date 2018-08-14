package ATM9_task;

import ATM9_task.bo.*;
import ATM9_task.emailpages.*;
import ATM8_task.util.MethodsForTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ATM8_task.util.CheckThat.emailIsDisplayedInDraft;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WritingEmailTest {
    private MainPage mainPage = page(MainPage.class);
    private LoginPage loginPage = page(LoginPage.class);
    private Header header = page(Header.class);
    private EmailPage emailPage = page(EmailPage.class);
    private EmailPopup emailPopup = page(EmailPopup.class);
    private LeftSection leftSection = page(LeftSection.class);
    private CenterPart centerPart = page(CenterPart.class);
    private Email email = new Email("alinaBlazhko@yandex.ru", "Email for test", "Hello Mr. Smith!");

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        getWebDriver().manage().timeouts().implicitlyWait(40, SECONDS);
        open("https://mail.yandex.ru/");
        mainPage.openLoginPage();
        loginPage.login(User.getUSER(), User.getPASSWORD());
    }

    @Test(description = "perform login email")
    public void writeEmailWithAllFields() throws InterruptedException {
        header.openNewEmail();
        emailPage.writeEmail(email);
        emailPage.closeEmail();
        emailPopup.closeEmailAndSaveAsDraft();
        leftSection.openDraftFolder();
        MethodsForTests.refreshPage();
        emailIsDisplayedInDraft();
    }
}
