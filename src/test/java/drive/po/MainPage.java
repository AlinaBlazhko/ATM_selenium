package drive.po;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class MainPage extends AbstractPage {
    private final static String URL = "https://disk.yandex.ru/";

    private By logInButton = By.xpath("//a[text()='Sign in']");

    public void open() {
        driver.get(URL);
    }

    public LoginPage clickOnButtonAuthorization(){
        waitForElementVisibility(logInButton);
        highlightElement(logInButton);
        driver.findElement(logInButton).click();
        return new LoginPage();
    }
}
