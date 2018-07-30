package po;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


/**
 * Created by X240 on 7/22/2018.
 */
public class Header extends AbstractPage {

    private By writeNewEmailButton = By.cssSelector("span.mail-ComposeButton-Text");
    private By refreshButton = By.cssSelector("span[title='Проверить, есть ли новые письма (F9)']");
    private By serviceMenuButton = By.cssSelector("span.nb");

//    public Header(WebDriver drive) {
//        super(drive);
//    }

    public EmailPage openNewEmail(){
        waitForElementVisibility(writeNewEmailButton);
        driver.findElement(writeNewEmailButton).click();
        return new EmailPage();
    }

    public ServiceMenuPage openServiceMenu(){
        waitForElementVisibility(serviceMenuButton);
        driver.findElement(serviceMenuButton).click();
        return new ServiceMenuPage();
    }

    public void refreshPage(){
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        new WebDriverWait(driver, 40).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    driver.findElement(refreshButton).click();
                } catch (StaleElementReferenceException e) {
                    System.out.println("Select failed! Try again...");
                    return false;
                }
                System.out.println("test found!");
                return true;
            }
        });
    }
}
