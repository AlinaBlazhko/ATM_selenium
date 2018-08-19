package ATM9_task.tests;

import ATM6_task.po.*;
import ATM8_task.util.MethodsForTests;
import ATM9_task.bo.Email;
import ATM9_task.bo.User;
import ATM9_task.emailpages.*;
import ATM9_task.emailpages.CenterPart;
import ATM9_task.emailpages.EmailPage;
import ATM9_task.emailpages.Header;
import ATM9_task.emailpages.LoginPage;
import ATM9_task.factorymethod.EmailFieldWithoutBody;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static ATM8_task.util.CheckThat.emailIsDisplayedInDraft;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by X240 on 8/19/2018.
 */
public class WritingEmailWithDecoratorPatternTest {
    private MainPage mainPage = page(MainPage.class);
    private LoginPage loginPage = page(LoginPage.class);
    private Header header = page(Header.class);
    private EmailPage emailPage = page(EmailPage.class);
    private EmailPopup emailPopup = page(EmailPopup.class);
    private LeftSection leftSection = page(LeftSection.class);
    private CenterPart centerPart = new CenterPart();
    private static final String URL = "https://mail.yandex.ru/";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        getWebDriver().manage().timeouts().implicitlyWait(40, SECONDS);
        open("https://mail.yandex.ru/");
        mainPage.openLoginPage();
        loginPage.login(User.getUSER(), User.getPASSWORD());
    }

    @Test(description = "write email with json and save as draft")
    public void writeEmailWithDecoratorPattern() throws InterruptedException {
        header.openNewEmail();
        emailPage.writeEmailWithDecorator();
        emailPage.closeEmail();
        emailPopup.closeEmailAndSaveAsDraft();
        leftSection.openDraftFolder();
        MethodsForTests.refreshPage();
        emailIsDisplayedInDraft();
    }

    @Test(description = "verify email's content", dependsOnMethods = "writeEmailWithDecoratorPattern")
    public void sentEmailAndVerifyThatEmailIsSent() {
        centerPart.openEmail();
        assertTrue(emailPage.isRecipientRight());
        assertTrue(emailPage.isSubjectRight("Email for test"));
        assertTrue(emailPage.isTextRight("Hello Mr. Smith!\n"));
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
