package ATM7_task.po.yandex.mail;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/22/2018.
 */
public class LoginPage extends AbstractPage{

    private static final String USER = "alinaBlazhko@yandex.ru";
    private static final String PASSWORD = "gfhjkmkzntcnf";

    private By userName = By.name("login");
    private By password = By.name("passwd");
    //button.passport-Button[type=submit]
    private By loginButton = By.xpath("//span[text()='Войти']");

    public void login(){
        waitForElementVisibility(userName);
        driver.findElement(userName).sendKeys(USER);
        driver.findElement(password).sendKeys(PASSWORD);
        driver.findElement(loginButton).click();
        waitForElementVisibility(By.xpath("//div[text()='alinaBlazhko']"));
    }

}
