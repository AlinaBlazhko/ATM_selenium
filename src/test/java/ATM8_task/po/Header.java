package ATM8_task.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header {
    @FindBy(css = "span.mail-ComposeButton-Text")
    private WebElement writeNewEmailButton;

    public void openNewEmail(){
        writeNewEmailButton.click();
    }
}
