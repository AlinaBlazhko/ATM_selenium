package ATM8_task.tests;

import ATM8_task.bo.User;
import ATM8_task.po.drivepages.*;
import ATM8_task.util.SelenideExtension;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ATM8_task.util.MethodsForTests.assertionWaitForTitle;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by X240 on 8/9/2018.
 */
public class DriveTest extends SelenideExtension {

    private AuthorizationDrivePage mainPage = page(AuthorizationDrivePage.class);
    private LoginPage loginPage = page(LoginPage.class);
    private LeftSideOfPage leftSideOfPage = page(LeftSideOfPage.class);
    private FilesPage filesPage = page(FilesPage.class);
    private TrashPage trashPage = page(TrashPage.class);

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        getWebDriver().manage().timeouts().implicitlyWait(40, SECONDS);
        open("https://disk.yandex.ru/");
        mainPage.clickOnButtonAuthorization();
        loginPage.login(User.getUSER(), User.getPASSWORD());
        assertionWaitForTitle("Яндекс.Диск");
        Assert.assertTrue(title().contains("Яндекс.Диск"));
    }

    @Test(description = "perform login email")
    public void login() {
        filesPage.dragAndDropSquare();
        filesPage.openTrash();
        refresh();
        trashPage.clickToFile();
        trashPage.restoreFile();

        filesPage.openContextMenu();
    }
}
