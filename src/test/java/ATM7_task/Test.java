package ATM7_task;

import ATM7_task.po.yandex.drive.FilesPage;
import ATM7_task.po.yandex.drive.LoginPage;
import ATM7_task.po.yandex.drive.MainPage;
import ATM7_task.po.yandex.drive.TrashPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import static ATM7_task.WebDriverSingleton.kill;

public class Test {

    //java -jar selenium-server-standalone-3.13.0.jar -role hub
    // java -Dwebdriver.chrome.driver=C:\Users\Alina_Blazhko\IdeaProjects\ATM_selenium\chromedriver.exe -jar selenium-server-standalone-3.13.0.jar -role node -hub http://10.66.171.45:4444/wd/hub

    @org.testng.annotations.Test(description = "perform login and verify that login successful")
    public void loginInEmailBox() {
        MainPage mainDraivePage = new MainPage();
        mainDraivePage.open();
        LoginPage loginPage = mainDraivePage.clickOnButtonAuthorization();
        FilesPage filesPage = loginPage.login();
        filesPage.dragNDropSquare();
        TrashPage trashPage = filesPage.openTrash();
        Assert.assertTrue(trashPage.isFileInTrash());
        trashPage.restoreFile();
        filesPage = trashPage.goBack();
        filesPage.dragNDropSquare();

    }

    @AfterTest
    public void after(){
        kill();
    }

}