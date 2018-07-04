import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

//import java.awt.*;

/**
 * Created by X240 on 7/3/2018.
 */
public class CommonTest {
    private static final String USER = "alinaBlazhko@yandex.ru";
    private static final String PASSWORD = "gfhjkmkzntcnf";
    private static final String SUBJECT = "Email for test";
    private static final String BODY = "Hello Mr. Smith!";
    private static final String URL = "https://mail.yandex.ru/";
    private Boolean until;
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
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        until = new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    WebElement refreshButton = driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']"));
                    refreshButton.click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });

        List<WebElement> check = driver.findElements(By.cssSelector("span._nb-checkbox-flag._nb-checkbox-normal-flag"));
//        WebElement check = draftButton.findElement(By.cssSelector("div.ns-view-toolbar-button-main-select-all.ns-view-id-144.js-toolbar-button.mail-Toolbar-Item.mail-Toolbar-Item_main-select-all.is-disabled"));
        WebElement deleteButton = driver.findElement(By.xpath("//span[text() = 'Удалить']"));
        if (!check.isEmpty()) {
            for (WebElement element : check) {
                element.click();
                deleteButton.click();
            }
        }else {
            System.out.println("Draft is empty!");
        }
    }

    @Test(dependsOnMethods = "loginTest")
    public void createEmailAndSaveAsDraft() {
        //open new email
        WebElement createEmailButton = driver.findElement(By.cssSelector("span.mail-ComposeButton-Text"));
        createEmailButton.click();

        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.mail-Compose-Field-Caption.mail-ui-Link.nb-with-s-right-gap.js-contact")));

        // send email yourself
        WebElement addressee = driver.findElement(By.cssSelector("span.mail-Compose-Field-Caption.mail-ui-Link.nb-with-s-right-gap.js-contact"));
        addressee.click();

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

        // open draft folder
        final WebElement draftButton = driver.findElement(By.xpath("//span[text() = 'Черновики']"));
        draftButton.click();

        // refresh draft folder
        final WebElement refreshButton = driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']"));
        until = new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    refreshButton.click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });

        //Assert that email appear in Draft folder
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Email for test']")));

        final WebElement email = driver.findElement(By.xpath("//span[text()='Email for test']"));

        Assert.assertTrue(email.isDisplayed());

        // Verify the draft content (addressee, subject and body – should be the same as in 3).
        until = new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
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

//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.mail-Bubble-Block_text")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Assert.assertTrue((driver.findElement(By.cssSelector("span.mail-Bubble-Block_text")).getText().equals("alinaBlazhko")));
        Assert.assertTrue(driver.findElement(By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev")).getAttribute("value").equals(SUBJECT));
        Assert.assertTrue((driver.findElement(By.cssSelector("textarea.cke_source.cke_reset.cke_enable_context_menu.cke_editable.cke_editable_themed.cke_contents_ltr")).getAttribute("value")).equals(BODY + "\n"));

        //Send the mail

        final WebElement sendButton = driver.findElement(By.xpath("//span[text() = 'Отправить']"));
        until = new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    sendButton.click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });

        // Verify, that the mail disappeared from ‘Drafts’ folder
        // open draft folder
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement draftButton2 = driver.findElement(By.cssSelector("span.mail-NestedList-Item-Name.js-folders-item-name"));
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.mail-NestedList-Item-Name.js-folders-item-name")));
        //        until = new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver webDriver) {
//                try {
//                    draftButton2.click();
//                } catch (StaleElementReferenceException e) {
//                    System.out.println("Select failed! Try again...");
//                    return false;
//                }
//                System.out.println("test found!");
//                return true;
//            }
//        });

//        Assert.assertFalse(email.isDisplayed());

    }


//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
}

