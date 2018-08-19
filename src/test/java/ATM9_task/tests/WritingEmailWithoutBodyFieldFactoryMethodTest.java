package ATM9_task.tests;

import ATM9_task.bo.*;
import ATM9_task.emailpages.*;
import ATM8_task.util.MethodsForTests;
import ATM9_task.factorymethod.EmailFieldWithoutBody;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static ATM8_task.util.CheckThat.emailIsDisplayedInDraft;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testng.Assert.assertTrue;

public class WritingEmailWithoutBodyFieldFactoryMethodTest {
    private MainPage mainPage = page(MainPage.class);
    private LoginPage loginPage = page(LoginPage.class);
    private Header header = page(Header.class);
    private EmailPage emailPage = page(EmailPage.class);
    private EmailPopup emailPopup = page(EmailPopup.class);
    private LeftSection leftSection = page(LeftSection.class);
    private CenterPart centerPart = page(CenterPart.class);
    private Email email = new Email("alinaBlazhko@yandex.ru", "Email for test", "Hello Mr. Smith!");

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        getWebDriver().manage().timeouts().implicitlyWait(40, SECONDS);
        open("https://mail.yandex.ru/");
        mainPage.openLoginPage();
        loginPage.login(User.getUSER(), User.getPASSWORD());
    }

    @Test(description = "write email without body and save as draft")
    public void writeEmailWithoutSubject() throws InterruptedException {
        header.openNewEmail();
        EmailFieldWithoutBody newEmail = new EmailFieldWithoutBody();
        emailPage.writeEmail(newEmail, email);
        emailPage.closeEmail();
        emailPopup.closeEmailAndSaveAsDraft();
        leftSection.openDraftFolder();
        MethodsForTests.refreshPage();
        emailIsDisplayedInDraft();
    }

    @Test(description = "verify email's content", dependsOnMethods = "writeEmailWithoutSubject")
    public void sentEmailAndVerifyThatEmailIsSent() {
        centerPart.openEmail();
        assertTrue(emailPage.isRecipientRight());
        assertTrue(emailPage.isSubjectRight("Email for test"));
        assertTrue(emailPage.isTextRight(""));
    }

    @Test(description = "send email and verify that email appear in Sent Folder", dependsOnMethods = "sentEmailAndVerifyThatEmailIsSent")
    public void sendingEmailFromDraft() {
        emailPage.sendEmail();
        leftSection.openSentFolder();
        header.refreshPage();
        assertTrue(leftSection.rightCountOfEmail());
    }

    @AfterClass(alwaysRun = true)
    public void after() {
        centerPart.deleteAllDraftsFromFolder();
        getWebDriver().quit();
    }
}
