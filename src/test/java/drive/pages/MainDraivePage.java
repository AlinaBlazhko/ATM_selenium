package drive.pages;

import org.openqa.selenium.By;

/**
 * Created by X240 on 7/28/2018.
 */
public class MainDraivePage extends AbstractPage {
    private final static String URL = "https://disk.yandex.ru/";

    private By logInButton = By.xpath("//a[text()='Sign in']");

    public void open() {
        driver.get(URL);
    }

    public LoginPage clickOnButtonAuthorization() {
        waitForElementVisibility(logInButton);
        highlightElement(logInButton);
        driver.findElement(logInButton).click();
        return new LoginPage();
    }
}
