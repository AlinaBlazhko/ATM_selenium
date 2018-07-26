package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by X240 on 7/23/2018.
 */
public class CenterPart extends AbstractPage{
    private By email = By.cssSelector("span.js-message-snippet-body");
    private By noEmailInFolderRow =By.xpath("//div[text()='В папке «Черновики» нет писем.']");
    private By selectEmail = By.cssSelector("label.nb-checkbox._nb-small-checkbox-checkbox._init");
    private By deleteButton = By.cssSelector("div[title='Удалить (Delete)']");

    public CenterPart(WebDriver driver) {
        super(driver);
    }


    public EmailPage openEmail(){
        driver.findElement(email).click();
        return new EmailPage(driver);
    }

    public boolean getNoEmailInFolderRow(){
        waitForElementVisibility(noEmailInFolderRow);
        return driver.findElement(noEmailInFolderRow).isDisplayed();
    }

    public void deleteEmail(){
        waitForElementVisibility(selectEmail);
        driver.findElement(selectEmail).click();
        driver.findElement(deleteButton).click();
    }
}
