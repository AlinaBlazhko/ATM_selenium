package ATM10_task.emailpages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private By loginButton = By.cssSelector(".HeadBanner-ButtonsWrapper a.HeadBanner-Button-Enter");

    public void openLoginPage(){
        $(loginButton).click();
    }
}
