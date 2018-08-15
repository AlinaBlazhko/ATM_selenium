package ATM9_task.emailpages;

import ATM9_task.bo.Email;
import ATM9_task.enums.TypeOfFillFields;
import ATM9_task.factorymethod.AbstractEmail;
import ATM9_task.factorymethod.EmailFactory;
import ATM9_task.factorymethod.EmailWithoutSubject;
import org.openqa.selenium.By;

import static ATM9_task.enums.TypeOfFillFields.ALL_FIELDS;
import static ATM9_task.enums.TypeOfFillFields.NO_SUBJECT;
import static com.codeborne.selenide.Selenide.$;

public class EmailPage {

    private By recipient = By.cssSelector("div.js-compose-field.mail-Bubbles");
    private By subject = By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev");
    private By email = By.cssSelector("textarea.cke_editable_themed.cke_contents_ltr");
    private By closeButton = By.xpath("//div[@title='Закрыть']");
    private By sentButton = By.xpath("//span[text() = 'Отправить']");

    public void writeEmailWithAllFields(Email newEmail){
        EmailFactory emailFactory = new EmailFactory();
        AbstractEmail email = emailFactory.getWriter(ALL_FIELDS);
        email.writeEmail(newEmail);
    }

    public void writeEmailWithoutSubject(Email newEmail){
        EmailFactory emailFactory = new EmailFactory();
        AbstractEmail email = emailFactory.getWriter(NO_SUBJECT);
        email.writeEmail(newEmail);
    }


    public void closeEmail() {
        $(closeButton).click();
    }

    public void sendEmail() {
        $(sentButton).click();
    }

    public boolean isRecipientRight() {
        return $("span.mail-Bubble-Block_text").getText().equals("alinaBlazhko");
    }

    public boolean isSubjectRight(String sub) {
        return $(subject).getValue().equals(sub);
    }

    public boolean isTextRight(String text) {
        System.out.println($(email).getValue());
        return $(email).getValue().equals(text);
    }
}
