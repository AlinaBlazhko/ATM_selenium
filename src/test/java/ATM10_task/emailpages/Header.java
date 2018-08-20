package ATM10_task.emailpages;

import org.openqa.selenium.By;

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
