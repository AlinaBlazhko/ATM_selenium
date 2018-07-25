package po;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        new WebDriverWait(driver, 40).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    driver.findElement(drafts).click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });
//        waitForElementVisibility(drafts);
//        driver.findElement(drafts).click();
    }

    public CenterPart openSents(){
        waitForElementVisibility(sents);
        driver.findElement(sents).click();
        return new CenterPart(driver);
    }
}
