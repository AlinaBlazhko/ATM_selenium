package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/22/2018.
 */
public class NewEmailPage extends AbstractPage{

    private static final String SUBJECT = "Email for test";
    private static final String BODY = "Hello Mr. Smith!";
    private By to = By.cssSelector("div.js-compose-field.mail-Bubbles");
    private By subject = By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev");
    private By letter = By.cssSelector("textarea.cke_source.cke_reset.cke_enable_context_menu.cke_editable.cke_editable_themed.cke_contents_ltr");
    private By closeButton = By.xpath("//div[@title='Закрыть']");

    public NewEmailPage(WebDriver driver) {
        super(driver);
    }
    
    public void writeEmail(){
        driver.findElement(to).sendKeys("alinaBlazhko@yandex.ru");
        driver.findElement(subject).sendKeys(SUBJECT);
        driver.findElement(letter).sendKeys(BODY);
    }

    public PopupPage closeEmail(){
        driver.findElement(closeButton).click();
        return new PopupPage(driver);
    }
}

