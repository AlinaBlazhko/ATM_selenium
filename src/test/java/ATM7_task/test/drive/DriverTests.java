package ATM7_task.test.drive;

import ATM7_task.po.drive.OptionsPopup;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DriverTests extends BaseTest {

    @Test(description = "Upload file images.jpg in drive")
    public void uploadFileToDrive() {
        loginPage = authorizationPage.clickOnButtonAuthorization();
        filesPage = loginPage.login();
        popup = filesPage.uploadFile();
        filesPage = popup.closePopup();
        actionBarPopup = filesPage.selectFile();
        Assert.assertTrue(actionBarPopup.isAmountOfFilesRight());
    }

    @Test(description = "download image.jpg and delete forever", dependsOnMethods = "uploadFileToDrive")
    public void downloadAndDeleteTest() {
        OptionsPopup options = filesPage.rightClickToElement();
        options.downloadFile();
        filesPage.dragAndDropSquare();
        trashPage = filesPage.openTrash();
        Assert.assertTrue(trashPage.isFileInTrash());
    }
}
