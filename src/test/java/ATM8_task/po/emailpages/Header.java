package ATM8_task.po.emailpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class Header {
    private By writeNewEmailButton = By.cssSelector("span.mail-ComposeButton-Text");
    private By refreshButton = By.cssSelector("span[title='Проверить, есть ли новые письма (F9)']");


    public void openNewEmail(){
        $(writeNewEmailButton).click();
    }
    public void refreshPage(){
        $(refreshButton).click();
    }
}
