package ATM9_task;

import ATM9_task.bo.Email;
import org.openqa.selenium.By;

public abstract class AbstractEmail {
    By recipient = By.cssSelector("div.js-compose-field.mail-Bubbles");
    By subject = By.cssSelector("input.mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev");
    By email = By.cssSelector("textarea.cke_editable_themed.cke_contents_ltr");

    public abstract void writeEmail(Email email);
}
