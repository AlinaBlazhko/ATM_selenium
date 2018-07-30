package po;

import org.openqa.selenium.By;

/**
 * Created by X240 on 7/22/2018.
 */
public class HomePage extends AbstractPage {

    private final static String URL = "https://mail.yandex.ru/";

    private By logInButton = By.cssSelector(".HeadBanner-ButtonsWrapper a.HeadBanner-Button-Enter");

    public void open() {
        driver.get(URL);
    }

    public LoginPage clickOnButtonAuthorization() {
        waitForElementVisibility(logInButton);
        driver.findElement(logInButton).click();
        return new LoginPage();
    }
}
