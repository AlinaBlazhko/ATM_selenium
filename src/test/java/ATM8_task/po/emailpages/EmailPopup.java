package ATM8_task.po.emailpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class EmailPopup {

    private By saveAsDraftAndCloseButton = By.xpath("//span[text()='Сохранить и перейти']");
    private By notSaveButton = By.xpath("//span[text()='Не сохранять']");
    private By cancelButton = By.xpath("//span[text()='Отмена']");

    public void closeEmailAndSaveAsDraft(){
        $(saveAsDraftAndCloseButton).click();
    }

}
