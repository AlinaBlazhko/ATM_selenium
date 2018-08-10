package ATM8_task.po.drivepages;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private By login = By.name("login");
    private By password = By.name("passwd");

    public void login(String user, String pwd) {
        $(login).val(user).pressTab();
        $(password).val(pwd).pressEnter();
    }
}