package ATM7_task;

import ATM7_task.po.mail.*;
import org.testng.annotations.BeforeTest;

/**
 * Created by X240 on 8/5/2018.
 */
public class BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    PopupPage popupPage;
    Header header;
    EmailPage newEmailPage;
    CenterPart centerPart;
    FoldersPage foldersPage;

    @BeforeTest
    public void setUp(){
        homePage = new HomePage();
        homePage.open();
        loginPage = homePage.clickOnButtonAuthorization();
        loginPage.login();
    }
}
