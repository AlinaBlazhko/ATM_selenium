package ATM8_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class Header {
    @FindBy(css = "span.mail-ComposeButton-Text")
    private WebElement writeNewEmailButton;

    public void openNewEmail(){
        $(By.cssSelector("span.mail-ComposeButton-Text")).click();
    }
}
