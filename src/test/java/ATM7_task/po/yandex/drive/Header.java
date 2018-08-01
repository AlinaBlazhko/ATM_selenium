package ATM7_task.po.yandex.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

public class Header extends AbstractPage {
    private By profileIcon = By.cssSelector("a.user2__current-account");

    public ProfileInfoPage openProfileInfo(){
        waitForElementVisibility(profileIcon);
        driver.findElement(profileIcon).click();
        return new ProfileInfoPage();
    }
}
