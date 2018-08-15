package ATM9_task;

import ATM9_task.bo.*;
import ATM9_task.emailpages.*;
import ATM8_task.util.MethodsForTests;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ATM8_task.util.CheckThat.emailIsDisplayedInDraft;
import static ATM9_task.enums.TypeOfFillFields.NO_SUBJECT;
import static com.codeborne.selenide.Selenide.*;
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

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        getWebDriver().manage().timeouts().implicitlyWait(40, SECONDS);
        open("https://mail.yandex.ru/");
        mainPage.openLoginPage();
        loginPage.login(User.getUSER(), User.getPASSWORD());
    }

    @Test(description = "perform login email")
    public void writeEmailWithoutSubject() throws InterruptedException {
        header.openNewEmail();
        emailPage.writeEmailWithoutSubject(email);
        emailPage.closeEmail();
        emailPopup.closeEmailAndSaveAsDraft();
        leftSection.openDraftFolder();
        MethodsForTests.refreshPage();
        emailIsDisplayedInDraft();
    }

//    @Test(description = "perform login email")
//    public void writeEmailWithAllFields() throws InterruptedException {
//        header.openNewEmail();
//        emailPage.writeEmailWithAllFields(email);
//        emailPage.closeEmail();
//        emailPopup.closeEmailAndSaveAsDraft();
//        leftSection.openDraftFolder();
//        MethodsForTests.refreshPage();
//        emailIsDisplayedInDraft();
//    }

    @AfterTest(alwaysRun = true)
    public void after(){
        By checkboxes = By.cssSelector("label.nb-checkbox._nb-small-checkbox-checkbox._init");
        By deleteButton = By.xpath("//span[text()='Удалить']");

        ElementsCollection checks = $$(checkboxes);
        if (!$$(checks).isEmpty()) {
            for (WebElement check : checks) {
                check.click();
            }
            $(deleteButton).click();
        }
        getWebDriver().quit();
    }
}
