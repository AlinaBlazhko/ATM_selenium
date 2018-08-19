package ATM9_task.divermanager;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.CHROME;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

/**
 * Created by X240 on 8/12/2018.
 */
public class WebDriverSingleton {

    private static WebDriver driver;

    private WebDriverSingleton(){

    }

    public static WebDriver getWebDriverInstance(){
        if (driver == null){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }
}
