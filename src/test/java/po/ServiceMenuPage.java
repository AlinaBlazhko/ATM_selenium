package po;

import drive.pages.FileFolderPage;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;


/**
 * Created by X240 on 7/28/2018.
 */
public class ServiceMenuPage extends AbstractPage {
    private By yandexDiscButton = By.cssSelector("._nb-popup-content a[href='https://disk.yandex.ru/?source=tab-mail']");

    public FileFolderPage openDriver(){
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        waitForElementVisibility(yandexDiscButton);
        driver.findElement(yandexDiscButton).click();
        return new FileFolderPage();
    }

}
