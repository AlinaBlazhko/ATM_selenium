package ATM7_task.po.yandex.mail;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PopupPage extends AbstractPage{

    private By saveAndOpenButton = By.xpath("//span[text()='Сохранить и перейти']");
    private By notSaveButton = By.xpath("//span[text()='Не сохранять']");
    private By cancelButton = By.xpath("//span[text()='Отмена']");

    public void closeAndSaveEmail(){
        driver.findElement(saveAndOpenButton).click();
    }
}
