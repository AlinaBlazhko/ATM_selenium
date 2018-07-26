import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import po.*;

import java.util.concurrent.TimeUnit;

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


    @BeforeClass(description = "start browser")
    public void iniDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "perform login and verify that login successful")
    public void loginInEmailBox() {
        homePage = new HomePage(driver);
        homePage.open();
        loginPage = homePage.clickOnButtonAuthorization();
        loginPage.login();
        assertTrue(driver.getTitle().contains("Входящие — Яндекс.Почта"));
    }

    @Test(description = "write new email and save as draft", dependsOnMethods = "loginInEmailBox")
    public void writeNewEmailTest() {
        header = new Header(driver);
        newEmailPage = header.openNewEmail();
        newEmailPage.writeEmail();
        newEmailPage.closeEmail();
        popupPage = new PopupPage(driver);
        popupPage.closeAndSaveEmail();

        // open draft folder
        foldersPage = new FoldersPage(driver);
        foldersPage.openDrafts();
        header.refreshPage();
        assertTrue(foldersPage.getCountOfEmailsInDraftFolder().isDisplayed());
    }

    @Test(description = "verify email's content", dependsOnMethods = "writeNewEmailTest")
    public void sentEmailAndVerifyThatEmailIsSent() {
        centerPart = new CenterPart(driver);
        newEmailPage = centerPart.openEmail();
        assertTrue(newEmailPage.getTo().equals("alinaBlazhko") || newEmailPage.getTo().equals("alinaBlazhko@yandex.ru"));
        assertTrue(newEmailPage.getSubject().equals("Email for test"));
        assertTrue(newEmailPage.getLetter().equals("Hello Mr. Smith!\n"));
    }


    @Test(description = "send email and verify that email appear in Sent Folder", dependsOnMethods = "sentEmailAndVerifyThatEmailIsSent")
    public void sendingEmailFromDraft(){
        newEmailPage.sentEmail();
        foldersPage.openSents();
        header.refreshPage();
        assertTrue(foldersPage.getCountOfEmailsInSentFolder().isDisplayed());
    }

    @AfterClass(description = "clear Sent folder for next test")
    public void clearSentFolder(){
        header.refreshPage();
        centerPart.deleteEmail();
        foldersPage.openDrafts();
        header.refreshPage();
        assertTrue(centerPart.getNoEmailInFolderRow());
    }

//    @AfterClass(description = "delete email from Sent folder and close browser")
//    public void closeBrowser(){
//        driver.quit();
//    }
}
