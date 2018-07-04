import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


import java.util.*;

//import java.awt.*;

import static com.sun.org.apache.xml.internal.serialize.LineSeparator.Web;
import static org.testng.Assert.assertTrue;

/**
 * Created by X240 on 7/3/2018.
 */
public class CommonTest {
    private static final String USER = "alinaBlazhko@yandex.ru";
    private static final String PASSWORD = "gfhjkmkzntcnf";
    private static final String SUBJECT = "Email for test";
    private static final String BODY = "Hello Mr. Smith!";
    private static final String URL = "https://mail.yandex.ru/";

    private WebDriver driver;


    @BeforeClass(description = "Init browser")
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();

    }


    @Test(description = "Login to the mail box")
    public void loginTest() throws InterruptedException {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.button2.button2_size_mail-big.button2_theme_mail-white.button2_type_link.HeadBanner-Button.HeadBanner-Button-Enter.with-shadow")));
        WebElement signInAccountButton = driver.findElement(By.cssSelector("a.button2.button2_size_mail-big.button2_theme_mail-white.button2_type_link.HeadBanner-Button.HeadBanner-Button-Enter.with-shadow"));
        signInAccountButton.click();

        // type user name
        WebElement userNameField = driver.findElement(By.name("login"));
        userNameField.sendKeys(USER);
        // type password
        WebElement passwordField = driver.findElement(By.name("passwd"));
        passwordField.sendKeys(PASSWORD);
        // click submit button
        WebElement submitButton = driver.findElement(By.xpath(".//button[@class='passport-Button']"));
        submitButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mail-User-Name")));

        assertTrue((driver.getCurrentUrl()).equals("https://mail.yandex.ru/?uid=670590425&login=alinablazhko#inbox"));

        //Make sure that draft folder is empty
        // refresh draft folder
        WebElement draftButton = driver.findElement(By.xpath("//span[text() = 'Черновики']"));
        draftButton.click();

        // refresh draft folder
//        new WebDriverWait(driver, 20)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']")));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        WebElement refreshButton = driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']"));
        Boolean until = new WebDriverWait( driver, 30 ).until( new ExpectedCondition<Boolean>()
        {
            public Boolean apply( WebDriver webDriver )
            {
                try
                {
                    driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']")).click();
                }
                catch( StaleElementReferenceException e )
                {
                    System.out.println( "Select failed! Try again..." );
                    return false;
                }
                System.out.println( "test found!" );
                return true;
            }
        } );

        List<WebElement> check = driver.findElements(By.cssSelector("span._nb-checkbox-flag._nb-checkbox-normal-flag"));
        for (WebElement element : check) {
            element.click();
        }
        WebElement deleteButton = driver.findElement(By.xpath("//span[text() = 'Удалить']"));
        deleteButton.click();
    }

    @Test(dependsOnMethods = "loginTest")
    public void createEmailAndSaveAsDraft() {
        //open new email
        WebElement createEmailButton = driver.findElement(By.cssSelector("span.mail-ComposeButton-Text"));
        createEmailButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.mail-Compose-Field-Caption.mail-ui-Link.nb-with-s-right-gap.js-contact")));

        // send email yourself
        WebElement sendSelf = driver.findElement(By.cssSelector("span.mail-Compose-Field-Caption.mail-ui-Link.nb-with-s-right-gap.js-contact"));
        sendSelf.click();

        // write email's subject
        WebElement subject = driver.findElement(By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev"));
        subject.sendKeys(SUBJECT);

        // fill email's text
        WebElement textEmail = driver.findElement(By.cssSelector("textarea.cke_source.cke_reset.cke_enable_context_menu.cke_editable.cke_editable_themed.cke_contents_ltr"));
        textEmail.sendKeys(BODY);

        //close letter
        WebElement closeButton = driver.findElement(By.xpath("//div[@title = 'Закрыть']"));
        closeButton.click();

        // in popup select "Сохранить и перейти"
        WebElement saveAsDraftButton = driver.findElement(By.xpath("//span[text() = 'Сохранить и перейти']"));
        saveAsDraftButton.click();

        // refresh draft folder
        WebElement draftButton = driver.findElement(By.xpath("//span[text() = 'Черновики']"));
        draftButton.click();

        // refresh draft folder
        WebElement refreshButton = driver.findElement(By.cssSelector("span.mail-ComposeButton-Refresh.js-left-col-toolbar-refresh.ns-action"));
        refreshButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_left.js-message-snippet-left")));

//        WebElement email = driver.findElement(By.className("span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_left.js-message-snippet-left"));
//        Assert.assertTrue(email.isDisplayed());

        List<WebElement> check = driver.findElements(By.cssSelector("span._nb-checkbox-flag._nb-checkbox-normal-flag"));
        Assert.assertTrue(check.size() == 1);

//        WebElement email = driver.findElement(By.className("span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_left.js-message-snippet-left"));
//        email.click();

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
