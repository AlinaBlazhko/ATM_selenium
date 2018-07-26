import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import po.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Created by X240 on 7/22/2018.
 */
public class PageObjectTest {

    private WebDriver driver;

    @BeforeClass(description = "start browser")
    public void iniDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "perform login")
    public void loginInemailBox() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        LoginPage loginPage = homePage.clickOnButtonAuthorization();
        loginPage.login();
        assertTrue(driver.getTitle().contains("Входящие — Яндекс.Почта"));
    }

    @Test(description = "write new email", dependsOnMethods = "loginInemailBox")
    public void writeNewEmailTest() {
        Header header = new Header(driver);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        EmailPage newEmailPage = header.openNewEmail();
        newEmailPage.writeEmail();
        PopupPage popupPage = newEmailPage.closeEmail();
        popupPage.closeAndSaveEmail();
        FoldersPage foldersPage = new FoldersPage(driver);
        foldersPage.openDrafts();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        header.refreshPage();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        header.refreshPage();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        assertTrue(driver.findElement(By.cssSelector(".mail-NestedList-Item_current span.mail-NestedList-Item-Info-Extras")).getText().equals("1"));
    }

    @Test(description = "send email from draft and verify that email is sent", dependsOnMethods = "writeNewEmailTest")
    public void sentEmailAndVerifyThatEmailIsSent() {
        CenterPart centerPart = new CenterPart(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        EmailPage newEmailPage = centerPart.openEmail();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        assertTrue(newEmailPage.getTo().equals("alinaBlazhko"));
        assertTrue(newEmailPage.getSubject().equals("Email for test"));
        assertTrue(newEmailPage.getLetter().equals("Hello Mr. Smith!\n"));

        newEmailPage.sentEmail();
        FoldersPage foldersPage = new FoldersPage(driver);
        foldersPage.openSents();
        Header header = new Header(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        header.refreshPage();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.cssSelector("a[title='Отправленные | одно письмо']")).isDisplayed());
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector("label.nb-checkbox._nb-small-checkbox-checkbox._init")).click();
        driver.findElement(By.cssSelector("div[title='Удалить (Delete)']")).click();

//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//        header.refreshPage();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        foldersPage.openDrafts();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        header.refreshPage();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        assertTrue(centerPart.getNoEmailInFolderRow());
    }


//    @AfterClass(description = "close browser")
//    public void closeBrowser(){
//        driver.quit();
//    }
}
