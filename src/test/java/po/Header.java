package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/22/2018.
 */
public class Header extends AbstractPage{

    private By writeNewEmailButton = By.cssSelector("span.mail-ComposeButton-Text");
    private By refreshButton = By.cssSelector("span[title='Проверить, есть ли новые письма (F9)']");

    public Header(WebDriver driver) {
        super(driver);
    }

    public EmailPage openNewEmail(){
        driver.findElement(writeNewEmailButton).click();
        return new EmailPage(driver);
    }

    public void refreshPage(){
        waitForElementVisibility(refreshButton);
        driver.findElement(refreshButton).click();
    }
}
