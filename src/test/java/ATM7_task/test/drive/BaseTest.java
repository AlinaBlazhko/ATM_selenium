package ATM7_task.test.drive;

import ATM7_task.po.drive.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static ATM7_task.WebDriverSingleton.kill;

/**
 * Created by X240 on 8/5/2018.
 */
public class BaseTest {

    LoginPage loginPage;
    FilesPage filesPage;
    TrashPage trashPage;
    AuthorizationDrivePage authorizationPage;
    UploadPopup popup;
    ActionBarPopup actionBarPopup;

    @BeforeTest
    public void setUp() {
        authorizationPage = new AuthorizationDrivePage();
        authorizationPage.open();

    }

    @AfterTest(description = "Close browser")
    public void after(){
        kill();
    }
}
