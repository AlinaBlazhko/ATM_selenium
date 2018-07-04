import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class TestBase {
    private static final String USER = "alinaBlazhko@yandex.ru";
    private static final String PASSWORD = "gfhjkmkzntcnf";
    private static final String SUBJECT = "Email for test";
    private static final String BODY = "Hello Mr. Smith!";
    private static final String URL = "https://mail.yandex.ru/";
    private Boolean until;


    @BeforeClass(description = "Init browser")
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    public void waitWhileActionIsPerformed(WebDriver driver){
//        Boolean until = new WebDriverWait( driver, 30 ).until(new ExpectedCondition<Boolean>()
//        {
//            public Boolean apply( WebDriver webDriver )
//            {
//                try
//                {
//                    driver.findElement(By.xpath("//span[@title='Проверить, есть ли новые письма (F9)']")).click();
//                }
//                catch( StaleElementReferenceException e )
//                {
//                    System.out.println( "Select failed! Try again..." );
//                    return false;
//                }
//                System.out.println( "test found!" );
//                return true;
//            }
//        } );
    }
}
