package ATM7_task.po.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

/**
 * Created by X240 on 8/5/2018.
 */
public class DeleteForeverPopup extends AbstractPage{

    private By deleteButton = By.className("js-confirmation-accept");//button.js-confirmation-accept
    private By cancelButton = By.className("js-confirmation-cancel");//button.js-confirmation-cancel

    public void acceptDelete(){
        waitForElementVisibility(deleteButton);
        driver.findElement(deleteButton).click();
    }

}
