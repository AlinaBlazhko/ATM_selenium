package ATM8_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class Header {
    private By writeNewEmailButton = By.cssSelector("span.mail-ComposeButton-Text");

    public void openNewEmail(){
        $(writeNewEmailButton).click();
    }
}
