package ATM6_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/22/2018.
 */
public class EmailPage extends AbstractPage{

    private static final String SUBJECT = "Email for test";
    private static final String BODY = "Hello Mr. Smith!";
    private By to = By.cssSelector("div.js-compose-field.mail-Bubbles");
    private By subject = By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev");
    private By letter = By.cssSelector("textarea.cke_editable_themed.cke_contents_ltr");
    private By closeButton = By.xpath("//div[@title='Закрыть']");
    private By sentButton = By.xpath("//span[text() = 'Отправить']");

//    public EmailPage(WebDriver driver) {
//        super(driver);
//    }
    
    public void writeEmail(){
        waitForElementVisibility(sentButton);
        driver.findElement(to).sendKeys("alinaBlazhko@yandex.ru");
        driver.findElement(subject).sendKeys(SUBJECT);
        driver.findElement(letter).sendKeys(BODY);
    }

    public PopupPage closeEmail(){
        driver.findElement(closeButton).click();
        return new PopupPage();
    }

    public void sentEmail(){
        waitForElementVisibility(sentButton);
        driver.findElement(sentButton).click();
    }

    public String getTo() {
        waitForElementVisibility(By.cssSelector("span.mail-Bubble-Block_text"));
        return driver.findElement(By.cssSelector("span.mail-Bubble-Block_text")).getText();
    }

    public String getSubject() {
        waitForElementVisibility(subject);
        return driver.findElement(subject).getAttribute("value");
    }

    public String getLetter() {
        waitForElementVisibility(letter);
        return driver.findElement(letter).getAttribute("value");
    }
}

