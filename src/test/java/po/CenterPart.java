package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/23/2018.
 */
public class CenterPart extends AbstractPage{
    private By email = By.cssSelector("span.js-message-snippet-body");
    private By selectAll = By.cssSelector("\n" +
            "div.ns-view-toolbar-button-main-select-all.ns-view-id-221.js-toolbar-button.mail-Toolbar-Item.mail-Toolbar-Item_main-select-all.is-disabled");

    public CenterPart(WebDriver driver) {
        super(driver);
    }


    public EmailPage openEmail(){
        driver.findElement(email).click();
        return new EmailPage(driver);
    }
    public void selectAll(){
        driver.findElement(selectAll).click();
    }
}
