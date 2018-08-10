package ATM8_task.po.drivepages;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

public class Header extends AbstractPage {
    private By profileIcon = By.cssSelector("a.user2__current-account");

    public ProfileInfoPage openProfileInfo(){
        waitForElementVisibility(profileIcon);
        driver.findElement(profileIcon).click();
        driver.findElements(By.cssSelector("a.menu__item_type_link")).get(0).click();
        return new ProfileInfoPage();
    }
}
