package ATM6_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ATM6_task.po.*;
import static java.util.concurrent.TimeUnit.SECONDS;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by X240 on 7/22/2018.
 */
public class PageObjectTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private PopupPage popupPage;
    private Header header;
    private EmailPage newEmailPage;
    private CenterPart centerPart;
    private FoldersPage foldersPage;
    private final static String URL = "https://mail.yandex.ru/";


    @BeforeClass(description = "start browser")
    public void iniDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
    }

    @Test(description = "perform login and verify that login successful")
    public void loginInEmailBox() {
        homePage = new HomePage();
        loginPage = new HomePage().clickOnButtonAuthorization();
        loginPage.login();
        assertTrue(driver.getTitle().contains("Входящие — Яндекс.Почта"));
    }

    @Test(description = "write new email and save as draft", dependsOnMethods = "loginInEmailBox")
    public void writeNewEmailTest() {
        header = new Header();
        newEmailPage = header.openNewEmail();
        newEmailPage.writeEmail();
        newEmailPage.closeEmail();
        popupPage = new PopupPage();
        popupPage.closeAndSaveEmail();

        // open draft folder
        foldersPage = new FoldersPage();
        foldersPage.openDrafts();
        header.refreshPage();
        assertTrue(foldersPage.getCountOfEmailsInDraftFolder());
    }

    @Test(description = "verify email's content", dependsOnMethods = "writeNewEmailTest")
    public void sentEmailAndVerifyThatEmailIsSent() {
        centerPart = new CenterPart();
        newEmailPage = centerPart.openEmail();
        assertTrue(newEmailPage.getTo().equals("alinaBlazhko") || newEmailPage.getTo().equals("alinaBlazhko@yandex.ru"));
        assertEquals(newEmailPage.getSubject(), "Email for test");
        assertEquals(newEmailPage.getLetter(), "Hello Mr. Smith!\n");
    }


    @Test(description = "send email and verify that email appear in Sent Folder", dependsOnMethods = "sentEmailAndVerifyThatEmailIsSent")
    public void sendingEmailFromDraft(){
        newEmailPage.sentEmail();
        foldersPage.openSents();
        header.refreshPage();
        assertTrue(foldersPage.getCountOfEmailsInSentFolder());
    }

    @AfterClass(description = "clear Sent folder for next test")
    public void clearSentFolder(){
        header.refreshPage();
        centerPart.deleteEmail();
        driver.quit();
    }

}
