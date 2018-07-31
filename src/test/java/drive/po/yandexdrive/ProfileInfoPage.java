package drive.po.yandexdrive;

import drive.po.AbstractPage;
import org.openqa.selenium.By;

public class ProfileInfoPage extends AbstractPage {

    private By settingsButton = By.cssSelector("a[href = 'https://yandex.ru/tune']");

    public void openSettings(){
        waitForElementVisibility(settingsButton);
        driver.findElement(settingsButton).click();
    }
}
