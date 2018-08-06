package ATM7_task.test.drive;

import ATM7_task.po.drive.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ATM7_task.WebDriverSingleton.kill;

public class DriveDeletingFileAndRestoringTest extends BaseTest {

    @Test(description = "Upload file test.json from resources package, download selecting files", groups = "second", singleThreaded = true)
    public void uploadFile() {
        loginPage = authorizationPage.clickOnButtonAuthorization();
        filesPage = loginPage.login();
        popup = filesPage.uploadFile();
        filesPage = popup.closePopup();
        actionBarPopup = filesPage.selectTwoFileFromList();
        actionBarPopup.downloadFiles();
        Assert.assertTrue(actionBarPopup.isAmountOfFilesRight());
    }

    @Test(dependsOnMethods = "uploadFile", groups = "second")
    public void test() {
        filesPage.clickToFirstFileFromList();
        actionBarPopup.clickDeleteButton();
        filesPage.clickTrashButton();
        DeleteForeverPopup deleteForeverPopup = actionBarPopup.deleteForever();
        deleteForeverPopup.acceptDelete();
    }
}
