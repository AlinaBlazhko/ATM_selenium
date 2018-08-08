package ATM8_task.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPopup {

    @FindBy(xpath = "//span[text()='Сохранить и перейти']")
    private WebElement saveAsDraftAndCloseButton;

    @FindBy(xpath = "//span[text()='Не сохранять']")
    private WebElement notSaveButton;

    @FindBy(xpath = "//span[text()='Отмена']")
    private WebElement cancelButton;

    public void closeEmailAndSaveAsDraft(){
        saveAsDraftAndCloseButton.click();
    }

}
