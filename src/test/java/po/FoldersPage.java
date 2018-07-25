package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by X240 on 7/22/2018.
 */
public class FoldersPage extends AbstractPage{
    private By drafts = By.xpath("//span[text()='Черновики']");
    private By sents = By.xpath("//span[text()='Отправленные']");


    public FoldersPage(WebDriver driver) {
        super(driver);
    }

    public void openDrafts(){
//        waitForElementVisibility(drafts);
        driver.findElement(drafts).click();
    }

    public CenterPart openSents(){
        waitForElementVisibility(sents);
        driver.findElement(sents).click();
        return new CenterPart(driver);
    }
}
