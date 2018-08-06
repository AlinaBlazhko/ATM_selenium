package ATM7_task.po.mail;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

/**
 * Created by X240 on 7/23/2018.
 */
public class CenterPart extends AbstractPage{
    private By email = By.cssSelector("span.js-message-snippet-body");
    private By noEmailInFolderRow =By.xpath("//div[text()='В папке «Черновики» нет писем.']");
    private By selectEmail = By.cssSelector("label.nb-checkbox._nb-small-checkbox-checkbox._init");
    private By deleteButton = By.cssSelector("div[title='Удалить (Delete)']");

    public EmailPage openEmail(){
        driver.findElement(email).click();
        return new EmailPage();
    }

    public void deleteEmail(){
        waitForElementVisibility(selectEmail);
        driver.findElement(selectEmail).click();
        driver.findElement(deleteButton).click();
    }
}
