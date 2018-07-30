package drive;

import drive.pages.FilesPage;
import drive.pages.LoginPage;
import drive.pages.MainDraivePage;
import drive.pages.TrashPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by X240 on 7/28/2018.
 */
public class YandexDriverTest {

    @Test(description = "perform login and verify that login successful")
    public void loginInEmailBox() {
        MainDraivePage mainDraivePage = new MainDraivePage();
        mainDraivePage.open();
        LoginPage loginPage = mainDraivePage.clickOnButtonAuthorization();
        FilesPage filesPage = loginPage.login();
        filesPage.dragNDropSquare();
        TrashPage trashPage = filesPage.openTrash();
        Assert.assertTrue(trashPage.isFileInTrash());
        trashPage.restoreFile();
        filesPage = trashPage.goBack();
    }
}
