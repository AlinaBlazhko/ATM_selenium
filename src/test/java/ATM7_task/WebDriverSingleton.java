package ATM7_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class WebDriverSingleton {

    private static WebDriver instance;

    private WebDriverSingleton() {
    }

    public static WebDriver getWebDriverInstance() throws MalformedURLException {
        if (instance != null) {
            return instance;
        }
        return instance = init();
    }

    private static WebDriver init(){
        WebDriver driver = null;
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");

//            capabilities.setCapability("handlesAlerts", false);
//            driver = new RemoteWebDriver(new URL("http://10.66.165.0:4444/wd/hub"), DesiredCapabilities.chrome());
            driver = new RemoteWebDriver(new URL(" http://192.168.137.1:4444/wd/hub"), capabilities);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void kill() {
        if (instance != null) {
            try {
                instance.quit();
            } catch (Exception e) {
                System.out.println("Cannot kill browser");
            } finally {
                instance = null;
            }
        }
    }


//    WebDriver driver;
//
//
//    public RemoteWebDriver getWebDriverInstance() throws MalformedURLException {
//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        driver = new RemoteWebDriver(new URL("http://10.66.171.45:1270/wd/hub"), cap);
//        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        return driver;
//    }
//
//    @Test
//    public void test(){
//
//    }
//
//    @AfterTest(alwaysRun = true)
//    public void after(){
//        driver.quit();
//    }
}
