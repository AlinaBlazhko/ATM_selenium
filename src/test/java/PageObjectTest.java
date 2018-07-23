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

//        driver.findElement(By.cssSelector(".mail-Toolbar-Item_main-deselect-all span.checkbox_view")).click();
    }

    @Test(description = "write new email", dependsOnMethods = "loginInemailBox")
    public void writeNewEmailTest(){
        Header header = new Header(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        NewEmailPage newEmailPage = header.openNewEmail();
        newEmailPage.writeEmail();
        PopupPage popupPage = newEmailPage.closeEmail();
        popupPage.closeAndSaveEmail();
    }

//    @AfterClass(description = "close browser")
//    public void closeBrowser(){
//        driver.quit();
//    }
}
