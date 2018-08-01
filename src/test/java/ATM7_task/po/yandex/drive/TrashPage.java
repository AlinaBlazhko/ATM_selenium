package ATM7_task.po.yandex.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

public class TrashPage extends AbstractPage {
    private By file = By.cssSelector(".listing-item__field_public-link");
    private By restoreButton = By.cssSelector("button.ufo-resources-action-bar__primary-button_desktop");

    public void restoreFile(){
        waitForElementVisibility(file);
        driver.findElement(file).click();
        waitForElementVisibility(restoreButton);
        driver.findElements(restoreButton).get(0).click();
    }

    public boolean isFileInTrash(){
        waitForElementVisibility(file);
        return driver.findElement(file).isDisplayed();
    }

    public FilesPage goBack(){
        driver.navigate().back();
        return new FilesPage();
    }
}
