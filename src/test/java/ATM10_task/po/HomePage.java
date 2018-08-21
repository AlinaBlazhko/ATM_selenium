package ATM10_task.po;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by X240 on 8/21/2018.
 */
public class HomePage extends AbstractPage{
    private final static String URL = "https://mail.yandex.ru/";
    private By logInButton = By.cssSelector(".HeadBanner-ButtonsWrapper a.HeadBanner-Button-Enter");

    public void open(){
        driver.get(URL);
    }

    public AuthorizationPage getLoginPage(){
        $(logInButton).click();
        return new AuthorizationPage();
    }


}
