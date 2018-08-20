package ATM10_task.divermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by X240 on 8/12/2018.
 */
public class WebDriverSingleton {

    private static WebDriver driver;

    public WebDriverSingleton(){

    }

    public static WebDriver getWebDriverInstance(){
        if (driver == null){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }
}
