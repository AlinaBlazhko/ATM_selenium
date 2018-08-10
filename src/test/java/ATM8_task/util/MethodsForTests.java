package ATM8_task.util;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Created by X240 on 8/8/2018.
 */
public class MethodsForTests {
        public static void refreshPage(){
            Selenide.refresh();
        }

        public static void highlight(SelenideElement element){
            executeJavaScript("arguments[0].style.border='3px solid green'", element);
        }
}
