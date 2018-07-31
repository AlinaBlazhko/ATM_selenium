package drive.po;

import org.openqa.selenium.By;

public class ProfileInfoPage extends AbstractPage{

    private By settingsButton = By.linkText("https://yandex.ru/tune");

    public void openSettings(){
        waitForElementVisibility(settingsButton);
        driver.findElement(settingsButton).click();
    }
}
