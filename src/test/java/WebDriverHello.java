import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by X240 on 6/30/2018.
 */
public class WebDriverHello {
    private static final String USER = "alinaBlazhko@yandex.ru";
    private static final String PASSWORD = "gfhjkmkzntcnf";
    private static final String SUBJECT = "Email for test";
    private static final String BODY = "Hello Mr. Smith!";
    private static final String URL = "https://mail.yandex.ru/";

    private WebDriver driver;


    @BeforeClass(description = "Init browser")
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "c:\\Users\\X240\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximize");
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
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.mail-ComposeButton.js-left-col-toolbar-compose")));
        assertTrue(driver.getCurrentUrl().equals("https://mail.yandex.ru/?uid=670590425&login=alinablazhko#inbox"));
    }

//    @Test(description = "Delete all old emails from draft folder", dependsOnMethods = "loginTest")
//    public void deleteOldEmailsFromDraft(){
//        new WebDriverWait(driver, 20)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Черновики']")));
//
//        WebElement draftButton = driver.findElement(By.xpath("//span[text()='Черновики']"));
//        draftButton.click();
//
//        List<WebElement> drafts = driver.findElements(By.cssSelector("div.ns-view-container-desc.mail-MessagesList.js-messages-list"));
//
//        System.out.println(drafts.size());
//    }

    @Test(description = "Create a new mail (fill addressee, subject and body fields) and save as draft", dependsOnMethods = "loginTest")
    public void writingNewEmailTest(){
        // open new email
        WebElement emailButton = driver.findElement(By.cssSelector("a.mail-ComposeButton.js-left-col-toolbar-compose"));
        emailButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.nb-button._nb-action-button._init.js-send-button.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")));
        // To:
        WebElement to = driver.findElement(By.cssSelector("span.mail-Compose-Field-Caption.mail-ui-Link.nb-with-s-right-gap.js-contact"));
        to.click();
        // Subject
        WebElement subject = driver.findElement(By.name("subj-e1ac2f662289f670556992795b2809a13640f7ba"));
        subject.sendKeys(SUBJECT);
        // Type email's text
        WebElement body = driver.findElement(By.cssSelector("div.cke_wysiwyg_div.cke_reset.cke_enable_context_menu.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders"));
        body.sendKeys(BODY);


        WebElement draftButton = driver.findElement(By.xpath("//span[text()='Черновики']"));
        draftButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.
                        cssSelector("button.nb-button._nb-small-action-button._init.nb-with-s-right-gap.js-resolve")));
        WebElement closeButton = driver.findElement(By.cssSelector("button.nb-button._nb-small-action-button._init.nb-with-s-right-gap.js-resolve"));
        closeButton.click();

        while(driver.findElements(By.xpath(".//span[text()='Email for test']")).size() == 0 )
            driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);

        WebElement draftEmail = driver.findElement(By.xpath(".//span[text()='Email for test']"));
        assertTrue(draftEmail.isDisplayed());

        draftEmail.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mail-Bubble-Block']/span[text()='alinaBlazhko@yandex.ru']")));

        WebElement recipient = driver.findElement(By.xpath("//span[@class='mail-Bubble-Block']/span[text()='alinaBlazhko@yandex.ru']"));
        assertTrue(recipient.isDisplayed());

        WebElement sub = driver.findElement(By.xpath("//input[@value='Email for test']"));
        assertTrue(sub.isDisplayed());

        WebElement text = driver.findElement(By.xpath("//div[text()='Hello Mr. Smith!']"));
        assertTrue(text.isDisplayed());

        WebElement sendButton = driver.findElement(By.xpath("//span[text()='Отправить']"));
        sendButton.click();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
