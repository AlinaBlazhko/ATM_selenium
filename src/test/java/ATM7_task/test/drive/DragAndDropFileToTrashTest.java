package ATM7_task.test.drive;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by X240 on 8/5/2018.
 */
public class DragAndDropFileToTrashTest extends BaseTest{
    @Test(description = "perform login and verify that login successful", groups = "first")
    public void loginInEmailBox() {
        loginPage = authorizationPage.clickOnButtonAuthorization();
        filesPage = loginPage.login();
        filesPage.dragAndDropSquare();
        trashPage = filesPage.openTrash();
        Assert.assertTrue(trashPage.isFileInTrash());
    }

    @Test(description = "Restore file from trash and make sure that file back to file folder",
            dependsOnMethods = "loginInEmailBox", groups = "first")
    public void restoreFileFromTrashPage(){
        trashPage.restoreFile();
        filesPage = trashPage.goBack();
        Assert.assertTrue(filesPage.isFileInFileFolder());
    }
}
