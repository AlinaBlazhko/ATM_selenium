package ATM8_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

   public void login(String user, String pwd){
       $(By.name("login")).val(user).pressTab();
       $(By.name("passwd")).val(pwd).pressEnter();
   }


}
