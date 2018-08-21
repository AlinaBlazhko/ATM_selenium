package ATM10_task.po;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by X240 on 8/21/2018.
 */
public class AuthorizationPage {
    private By login = By.name("login");
    private By password = By.name("passwd");
    private static final String USER = "alinaBlazhko@yandex.ru";
    private static final String PASSWORD = "gfhjkmkzntcnf";

    public void login(){
        $(login).val(USER).pressTab();
        $(password).val(PASSWORD).pressEnter();
    }
}
