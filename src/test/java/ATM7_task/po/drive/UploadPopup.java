package ATM7_task.po.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

/**
 * Created by X240 on 8/5/2018.
 */
public class UploadPopup extends AbstractPage {
    private By uploadOtherButton = By.cssSelector("label[title='Загрузить ещё']");
    private By closeButton = By.cssSelector("a._nb-popup-close");

    public FilesPage closePopup(){
        waitForElementVisibility(closeButton);
        highlightElement(closeButton);
        driver.findElement(closeButton).click();
        return new FilesPage();
    }

}
