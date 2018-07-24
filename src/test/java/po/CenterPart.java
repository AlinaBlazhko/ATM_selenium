package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/23/2018.
 */
public class CenterPart extends AbstractPage{
    private By email = By.cssSelector("span.js-message-snippet-body");

    public CenterPart(WebDriver driver) {
        super(driver);
    }

    public EmailPage openEmail(){
        driver.findElement(email).click();
        return new EmailPage(driver);
    }

}
