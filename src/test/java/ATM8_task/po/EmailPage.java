package ATM8_task.po;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class EmailPage {

    private By recipient = By.cssSelector("div.js-compose-field.mail-Bubbles");
    private By subject = By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev");
    private By email = By.cssSelector("textarea.cke_editable_themed.cke_contents_ltr");
    private By closeButton = By.xpath("//div[@title='Закрыть']");
    private By sentButton = By.xpath("//span[text() = 'Отправить']");

    public void writeEmail(String address, String subj, String body) {
        $(recipient).val(address).pressTab();
        $(subject).val(subj).pressTab();
        $(email).val(body);
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
