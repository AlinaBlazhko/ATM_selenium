import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import po.*;

/**
 * Created by X240 on 7/22/2018.
 */
public class PageObjectTest {

    private WebDriver driver;

    @BeforeClass(description = "start browser")
    public void iniDriver(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "perform login")
    public void loginInemailBox(){
        HomePage homePage = new HomePage(driver);
        homePage.open();
        LoginPage loginPage = homePage.clickOnButtonAuthorization();
        loginPage.login();
        Assert.assertTrue(driver.getTitle().contains("Входящие — Яндекс.Почта"));
    }

    @Test(description = "write new email", dependsOnMethods = "loginInemailBox")
    public void writeNewEmailTest(){
        Header header = new Header(driver);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        EmailPage newEmailPage = header.openNewEmail();
        newEmailPage.writeEmail();
        PopupPage popupPage = newEmailPage.closeEmail();
        popupPage.closeAndSaveEmail();
        FoldersPage foldersPage = new FoldersPage(driver);
        foldersPage.openDrafts();
        header.refreshPage();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector(".mail-NestedList-Item_current span.mail-NestedList-Item-Info-Extras")).getText().equals("1"));
    }

    @Test(description = "send email from draft and verify that email is sent", dependsOnMethods = "writeNewEmailTest")
    public void sentEmailAndVerifyThatEmailIsSent(){
        CenterPart centerPart = new CenterPart(driver);
        EmailPage newEmailPage = centerPart.openEmail();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector("span.mail-Bubble-Block_text")).getText().equals("alinaBlazhko"));
        Assert.assertTrue(newEmailPage.getSubject().equals("Email for test"));
//        System.out.println(driver.findElement(By.cssSelector("textarea.cke_source.cke_reset.cke_enable_context_menu.cke_editable.cke_editable_themed.cke_contents_ltr")).getText());
//        Assert.assertTrue(driver.findElement(By.cssSelector("textarea.cke_source.cke_reset.cke_enable_context_menu.cke_editable.cke_editable_themed.cke_contents_ltr")).getText().equals("Hello Mr. Smith!"));

        newEmailPage.sentEmail();
        FoldersPage foldersPage = new FoldersPage(driver);
        foldersPage.openSents();
        Header header = new Header(driver);
//        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        header.refreshPage();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector(".mail-NestedList-Item_current span.mail-NestedList-Item-Info-Extras")).getText().equals("1"));

        driver.findElement(By.cssSelector(".mail-Toolbar-Item_main-select-all input.checkbox_controller")).click();
    }


//    @AfterClass(description = "close browser")
//    public void closeBrowser(){
//        driver.quit();
//    }
}
