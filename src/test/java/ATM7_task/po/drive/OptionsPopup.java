package ATM7_task.po.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

public class OptionsPopup extends AbstractPage{
    private By options = By.cssSelector(".resources-actions-popup__actions-menu div.menu__item");

    public void downloadFile(){
        waitForElementVisibility(options);
        driver.findElements(options).get(1).click();
    }
}
