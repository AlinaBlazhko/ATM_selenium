package ATM7_task.test.drive;

import ATM7_task.assertions.CheckThat;
import ATM7_task.po.drive.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DriverTests extends BaseTest {
    private FilesPage filesPage;

    @Test(description = "Upload file images.jpg in drivepages")
    public void uploadFileToDrive() {
        LoginPage loginPage = new AuthorizationDrivePage().clickOnButtonAuthorization();
        filesPage = loginPage.login();
        UploadPopup popup = filesPage.uploadFile();
        filesPage = popup.closePopup();
        filesPage.selectFile();
        CheckThat.fileIsDisplayed();
    }

    @Test(description = "download image.jpg and delete forever", dependsOnMethods = "uploadFileToDrive")
    public void downloadAndDeleteTest() {
        filesPage.rightClickToElement();
        new OptionsPopup().downloadFile();
        filesPage.dragAndDropSquare();
        filesPage.openTrash();
        CheckThat.isFileInTrash();
    }
}
