package drive.po;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractPage{
    private By profileIcon = By.cssSelector("a.user2__current-account");

    public ProfileInfoPage openProfileInfo(){
        waitForElementVisibility(profileIcon);
        driver.findElement(profileIcon).click();
        return new ProfileInfoPage();
    }
}
