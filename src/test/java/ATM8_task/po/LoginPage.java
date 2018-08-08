package ATM8_task.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    @FindBy(name = "login")
    private WebElement login;

    @FindBy(name = "passwd")
    private WebElement password;

    @FindBy(xpath = "//span[text()='Войти']")
    private WebElement submitButton;

   public void login(String user, String pwd){
       login.sendKeys(user);
       password.sendKeys(pwd);
       submitButton.click();
   }


}
