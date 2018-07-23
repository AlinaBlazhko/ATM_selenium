package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/22/2018.
 */
public class Header extends AbstractPage{

    private By writeNewEmailButton = By.className("a.mail-ComposeButton.js-left-col-toolbar-compose");

    public Header(WebDriver driver) {
        super(driver);
    }

    public NewEmailPage openNewEmail(){
        driver.findElement(writeNewEmailButton).click();
        return new NewEmailPage(driver);
    }
}
