package ATM8_task.po.emailpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private By login = By.name("login");
    private By password = By.name("passwd");

   public void login(String user, String pwd){
       $(login).val(user).pressTab();
       $(password).val(pwd).pressEnter();
   }


}
