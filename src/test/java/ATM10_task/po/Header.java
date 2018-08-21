package ATM10_task.po;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by X240 on 8/21/2018.
 */
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
