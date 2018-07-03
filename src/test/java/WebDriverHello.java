import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.mail-ComposeButton.js-left-col-toolbar-compose")));
        assertTrue(driver.getCurrentUrl().equals("https://mail.yandex.ru/?uid=670590425&login=alinablazhko#inbox"));
    }

    @Test(description = "Make sure that Draft folder is empty", dependsOnMethods = "loginTest")
    public void makeSureThatDraftFolderIsEmpty(){
        //Delete all old drafts
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement draftButton = driver.findElement(By.xpath("//span[text()='Черновики']"));
        draftButton.click();
        List<WebElement> selectAll = driver.findElements(By.cssSelector("span._nb-checkbox-flag._nb-checkbox-normal-flag"));
        if (selectAll.size() != 0){
        for (WebElement element: selectAll){
            element.click();
        }}
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement deleteButton = draftButton.findElement(By.xpath("//span[text()='Удалить']"));
        deleteButton.click();
    }

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
        WebElement body = driver.findElement(By.cssSelector("textarea.cke_source.cke_reset.cke_enable_context_menu.cke_editable.cke_editable_themed.cke_contents_ltr"));
        body.sendKeys(BODY);


        WebElement draftButton = driver.findElement(By.xpath("//span[text()='Черновики']"));
        draftButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.
                        cssSelector("button.nb-button._nb-small-action-button._init.nb-with-s-right-gap.js-resolve")));

        WebElement closeButton = driver.findElement(By.cssSelector("button.nb-button._nb-small-action-button._init.nb-with-s-right-gap.js-resolve"));
        closeButton.click();

        WebElement refreshListButton = driver.findElement(By.cssSelector("span.mail-ComposeButton-Refresh.js-left-col-toolbar-refresh.ns-action"));
        refreshListButton.click();

        List<WebElement> draftEmail = driver.findElements(By.cssSelector("span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_body.js-message-snippet-body"));
        assertTrue(draftEmail.size() == 1);

        draftEmail.get(0).click();

//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mail-Bubble-Block']/span[text()='alinaBlazhko@yandex.ru']")));

        WebElement recipient = driver.findElement(By.xpath("//span[@class='mail-Bubble-Block']/span[text()='alinaBlazhko@yandex.ru']"));
        assertTrue(recipient.equals(USER));

        WebElement sub = driver.findElement(By.xpath("//input[@value='Email for test']"));
        assertTrue(sub.equals(SUBJECT));

        WebElement text = driver.findElement(By.xpath("//div[text()='Hello Mr. Smith!']"));
        assertTrue(text.equals(BODY));

        WebElement sendButton = driver.findElement(By.xpath("//span[text()='Отправить']"));
        sendButton.click();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
