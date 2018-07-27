package po;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by X240 on 7/22/2018.
 */
public class FoldersPage extends AbstractPage{
    private By drafts = By.xpath("//span[text()='Черновики']");
    private By sents = By.xpath("//span[text()='Отправленные']");
    private By countOfEmailsInDraftFolder = By.cssSelector("a[title='Черновики | одно письмо']");
    private By countOfEmailsInSentFolder = By.cssSelector("a[title='Отправленные | одно письмо']");

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
    }

    public boolean getCountOfEmailsInSentFolder(){
        waitForElementVisibility(countOfEmailsInDraftFolder);
        return driver.findElement(countOfEmailsInDraftFolder).isDisplayed();
    }

    public CenterPart openSents(){
        waitForElementVisibility(sents);
        driver.findElement(sents).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        return new CenterPart(driver);
    }

    public boolean getCountOfEmailsInDraftFolder(){
        waitForElementVisibility(countOfEmailsInDraftFolder);
        return driver.findElement(countOfEmailsInDraftFolder).isDisplayed();
    }

}
