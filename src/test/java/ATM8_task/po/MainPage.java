package ATM8_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
//    $(By.cssSelector(".HeadBanner-ButtonsWrapper a.HeadBanner-Button-Enter")).click();
    @FindBy(css = ".HeadBanner-ButtonsWrapper a.HeadBanner-Button-Enter")
    private WebElement signInButton;

    public void openLoginPage(){
        signInButton.click();
    }
}
