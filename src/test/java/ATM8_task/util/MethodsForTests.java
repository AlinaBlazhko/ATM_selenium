package ATM8_task.util;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.DriverManager;

/**
 * Created by X240 on 8/8/2018.
 */
public class MethodsForTests {
        public static void refreshPage(){
            Selenide.refresh();
        }
}
