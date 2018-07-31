package mail.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PopupPage extends AbstractPage{

    private By saveAndOpenButton = By.xpath("//span[text()='Сохранить и перейти']");
    private By notSaveButton = By.xpath("//span[text()='Не сохранять']");
    private By cancelButton = By.xpath("//span[text()='Отмена']");

    public PopupPage(WebDriver driver) {
        super(driver);
    }

    public void closeAndSaveEmail(){
        driver.findElement(saveAndOpenButton).click();
    }
}
