package ATM7_task.po.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

public class AuthorizationDrivePage extends AbstractPage {
    private final static String URL = "https://disk.yandex.ru/";

    private By logInButton = By.xpath("//a[text()='Sign in']");

    public void open() {
        driver.get("https://disk.yandex.ru/");
//        waitForElementVisibility(logInButton);
    }

    public LoginPage clickOnButtonAuthorization(){
        waitForElementVisibility(logInButton);
        highlightElement(logInButton);
        driver.findElement(logInButton).click();
        return new LoginPage();
    }
}
