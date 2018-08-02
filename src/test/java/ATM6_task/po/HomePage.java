package ATM6_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/22/2018.
 */
public class HomePage extends AbstractPage {

    private final static String URL = "https://ATM6_task.yandex.ru/";

    private By logInButton = By.cssSelector(".HeadBanner-ButtonsWrapper a.HeadBanner-Button-Enter");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public LoginPage clickOnButtonAuthorization() {
        waitForElementVisibility(logInButton);
        driver.findElement(logInButton).click();
        return new LoginPage(driver);
    }
}