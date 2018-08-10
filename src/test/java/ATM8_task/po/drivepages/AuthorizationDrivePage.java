package ATM8_task.po.drivepages;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

import static ATM8_task.util.MethodsForTests.highlight;
import static com.codeborne.selenide.Selenide.$;

public class AuthorizationDrivePage{

    private By logInButton = By.xpath("//a[text()='Sign in']");

    public void clickOnButtonAuthorization(){
        highlight($(logInButton));
        $(logInButton).click();
    }
}
