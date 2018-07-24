package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/23/2018.
 */
public class CenterPart extends AbstractPage{
    private By email = By.cssSelector("span.js-message-snippet-body");
    private By selectAll = By.cssSelector("div[data-key='view=toolbar-button-main-select-all&current_folder=6&sort_type=date&layout=messages&id=main-select-all']");

    public CenterPart(WebDriver driver) {
        super(driver);
    }


    public EmailPage openEmail(){
        driver.findElement(email).click();
        return new EmailPage(driver);
    }
    public void selectAll(){
        driver.findElement(selectAll).click();
        driver.findElement(selectAll).click();
    }
}
