package ATM8_task.tests;

import ATM8_task.bo.Email;
import ATM8_task.bo.User;
import ATM8_task.po.emailpages.*;
import ATM8_task.util.MethodsForTests;
import ATM8_task.util.SelenideExtension;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ATM8_task.util.CheckThat.emailIsDisplayedInDraft;
import static ATM8_task.util.CheckThat.justOneEmailInFolder;
import static ATM8_task.util.CheckThat.userIsSignInEmail;
import static ATM8_task.util.MethodsForTests.deleteAllEmailsFromFolder;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testng.Assert.assertTrue;


public class EmailTests extends SelenideExtension {

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
    }

    @Test(description = "perform login email")
    public void login() throws InterruptedException {
        mainPage.openLoginPage();
        loginPage.login(User.getUSER(), User.getPASSWORD());
        userIsSignInEmail();
    }

    @Test(description = "write email and save as draft",
            dependsOnMethods = "login")
    public void writeEmailAndSafeAsDraft() {
        header.openNewEmail();
        emailPage.writeEmail(email);
        emailPage.closeEmail();
        emailPopup.closeEmailAndSaveAsDraft();
        leftSection.openDraftFolder();
        MethodsForTests.refreshPage();
        emailIsDisplayedInDraft();
    }

    @Test(description = "open draft, verify email's content and send folder",
            dependsOnMethods = "writeEmailAndSafeAsDraft")
    public void sendingDraftEmail() {
        centerPart.openDraftEmail();
        assertTrue(emailPage.isRecipientRight());
        assertTrue(emailPage.isSubjectRight(email.getSubject()));
        assertTrue(emailPage.isTextRight("Hello Mr. Smith!\n"));
        emailPage.sendEmail();
        leftSection.openSentFolder();
        header.refreshPage();
        justOneEmailInFolder();
    }

    @AfterTest(alwaysRun = true)
    public void after() {
        leftSection.openSentFolder();
        deleteAllEmailsFromFolder();
    }
}
