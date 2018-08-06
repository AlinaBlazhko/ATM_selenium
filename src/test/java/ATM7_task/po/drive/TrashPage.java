package ATM7_task.po.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class TrashPage extends AbstractPage {
    private By file = By.cssSelector("div.listing-item__info");
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