package ATM8_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public void openLoginPage(){
        $(".HeadBanner-ButtonsWrapper a.HeadBanner-Button-Enter").click();
    }
}
