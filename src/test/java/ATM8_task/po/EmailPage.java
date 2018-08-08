package ATM8_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage {

//    private static final String SUBJECT = "Email for test";
//    private static final String BODY = "Hello Mr. Smith!";
//    private By to = By.cssSelector("div.js-compose-field.mail-Bubbles");
//    private By subject = By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev");
//    private By letter = By.cssSelector("textarea.cke_editable_themed.cke_contents_ltr");
//    private By closeButton = By.xpath("//div[@title='Закрыть']");
//    private By sentButton = By.xpath("//span[text() = 'Отправить']");

    @FindBy(css = "div.js-compose-field.mail-Bubbles")
    private WebElement recipient;

    @FindBy(css = "input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev")
    private WebElement subject;

    @FindBy(css = "textarea.cke_editable_themed.cke_contents_ltr")
    private WebElement email;

    @FindBy(xpath = "//div[@title='Закрыть']")
    private WebElement closeButton;

    public void writeEmail(String address, String subj, String body){
        recipient.sendKeys(address);
        subject.sendKeys(subj);
        email.sendKeys(body);
    }

    public void closeEmail(){
        closeButton.click();
    }
}
