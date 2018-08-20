package ATM10_task;

import ATM10_task.divermanager.WebDriverSingleton;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import com.codeborne.selenide.*;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 8/19/2018.
 */
public class Stepdefs {

    WebDriverSingleton driver = new WebDriverSingleton();

    @Given("^I sign in in email with <username> and <password>$")
    public void iSignInInEmailWithUsernameAndPassword() throws Throwable {
//        WebDriver.
    }
}
