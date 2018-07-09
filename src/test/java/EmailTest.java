import javafx.scene.web.WebView;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


/**
 * Created by X240 on 7/8/2018.
 */
public class EmailTest {
    private WebDriver driver;
    private static final String USER = "alinaBlazhko@yandex.ru";
    private static final String PASSWORD = "gfhjkmkzntcnf";
    private static final String SUBJECT = "Email for test";
    private static final String BODY = "Hello Mr. Smith!";
    private static final String URL = "https://mail.yandex.ru/";


    @BeforeClass
    public void before() {
        driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
    }

    @Test(description = "perform successfully login for credential")
    public void loginTest() {
        //Click to button login
        WebElement loginButton = driver.findElement(By.cssSelector("a.button2.button2_size_mail-big.button2_theme_mail-white.button2_type_link.HeadBanner-Button.HeadBanner-Button-Enter.with-shadow"));
        loginButton.click();

        assertTrue(driver.getTitle().equals("Авторизация"));

        //type user name and pwd, click submit button
        WebElement login = driver.findElement(By.name("login"));
        login.sendKeys(USER);

        WebElement password = driver.findElement(By.name("passwd"));
        password.sendKeys(PASSWORD);

        WebElement submitButton = driver.findElement(By.xpath("//span[text() = 'Войти']"));
        submitButton.click();


        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mail-User-Name")));

        // Verify that we login successfully
        assertTrue(driver.getTitle().contains("Входящие — Яндекс.Почта"));
    }

    @Test(description = "loginTest")
    public void writingEmailAndSavingAsDraft() throws InterruptedException {
        // click in "write email button"
        WebElement createEmailButton = driver.findElement(By.cssSelector("span.mail-ComposeButton-Text"));
        createEmailButton.click();

        //wait when page loads
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //type fields "Кому", "Тема" and "Письмо"
        WebElement to = driver.findElement(By.xpath("//div[@name='to']"));
        to.sendKeys(USER);

        WebElement subject = driver.findElement(By.name("subj-e1ac2f662289f670556992795b2809a13640f7ba"));
        subject.sendKeys(SUBJECT);

        WebElement textEmail = driver
                .findElement(By.cssSelector("textarea.cke_source.cke_reset.cke_enable_context_menu.cke_editable.cke_editable_themed.cke_contents_ltr"));
        textEmail.sendKeys(BODY);

        //open email
        WebElement closeButton = driver
                .findElement(By.xpath("//div[@title='Закрыть']"));
        closeButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._nb-popup-i")));

        // work with popup
        WebElement saveAndClose = driver.findElement(By.xpath("//span[text()='Сохранить и перейти']"));
        saveAndClose.click();

        //open draft folder
        WebElement draftButton = driver.findElement(By.xpath("//span[text()='Черновики']"));
        draftButton.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        final WebElement refreshButton = driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']"));

        //click refresh button
        new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']")).click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });

        WebElement drafts = driver.findElement(By.xpath("//a[@title='Черновики | одно письмо']"));
        assertTrue(drafts.isDisplayed());

        //open our email in draft
        final WebElement email = driver.findElement(By.cssSelector("span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_subject"));
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    email.click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });

        //verify that fields "Кому", "Тема" and "Текст письма" is correct
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        assertTrue((driver.findElement(By.xpath("//span[@data-yabble-email='alinaBlazhko@yandex.ru']"))).isDisplayed());
        assertTrue(driver.findElement(By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev")).getAttribute("Value")
                .equals("Email for test"));
//        System.out.println(driver.findElement(By.id("cke_56_contents_wrap")).getText());
//        assertTrue((driver.findElement(By.cssSelector("div.cke_contents_wrap"))).getText().equals(BODY));

        // send email and verify that email appears in sent folder
        List<WebElement> sendButtons = driver.findElements(By.xpath("//span[text() = 'Отправить']"));
        sendButtons.get(0).click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        final WebElement sentFolder = driver.findElement(By.xpath("//span[text()='Отправленные']"));

        //open Sent folder and verify that our email appears
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    sentFolder.click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });

        //click refresh button
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']")).click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });

        WebElement sentEmails = driver.findElement(By.xpath("//a[@title='Отправленные | одно письмо']"));
        assertTrue(sentEmails.isDisplayed());

        //open Draft folder and verify that our email disappears
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']")).click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });

        while (!driver.getTitle().contains("Черновики — Яндекс.Почта")){
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Черновики']")).click();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.xpath("//div[text()='В папке «Черновики» нет писем.']")).isDisplayed());
    }

    @AfterClass
    public void after(){
        driver.quit();
    }
}

