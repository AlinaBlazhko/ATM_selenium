package ATM7_task.assertions;

import ATM7_task.po.drive.ActionBarPopup;
import ATM7_task.po.drive.TrashPage;
import org.testng.Assert;

/**
 * Created by X240 on 8/11/2018.
 */
public class CheckThat {

    public static void fileIsDisplayed(){
        ActionBarPopup actionBarPopup = new ActionBarPopup();
        Assert.assertTrue(actionBarPopup.isAmountOfFilesRight());
    }

    public static void isFileInTrash(){
        TrashPage trashPage = new TrashPage();
        Assert.assertTrue(trashPage.isFileInTrash());
    }
}
