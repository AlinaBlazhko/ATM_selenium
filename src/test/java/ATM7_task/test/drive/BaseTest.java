package ATM7_task.test.drive;

import ATM7_task.po.drive.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;

import static ATM7_task.WebDriverSingleton.getWebDriverInstance;
import static ATM7_task.WebDriverSingleton.kill;

/**
 * Created by X240 on 8/5/2018.
 */
public class BaseTest {



    @BeforeTest
    public void setUp() {
        try {
            getWebDriverInstance().get("https://disk.yandex.ru/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @AfterTest(description = "Close browser")
    public void after(){
        new TrashPage().deleteAllFroMTrash();
//        new ActionBarPopup().clickDeleteButton();
        kill();
    }
}
