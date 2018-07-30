package po;

import org.openqa.selenium.By;

public class PopupPage extends AbstractPage {

    private By saveAndOpenButton = By.xpath("//span[text()='Сохранить и перейти']");
    private By notSaveButton = By.xpath("//span[text()='Не сохранять']");
    private By cancelButton = By.xpath("//span[text()='Отмена']");

//    public PopupPage(WebDriver drive) {
//        super(drive);
//    }

    public void closeAndSaveEmail(){
        driver.findElement(saveAndOpenButton).click();
    }
}
