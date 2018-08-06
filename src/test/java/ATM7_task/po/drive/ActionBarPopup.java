package ATM7_task.po.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by X240 on 8/5/2018.
 */
public class ActionBarPopup extends AbstractPage{
    private By deleteForeverButton = By.cssSelector("button.ufo-resources-action-bar__primary-button_desktop");
        private By downloadButton = By.cssSelector("//span[text()='Скачать']");
    private By deleteAndDownloadButtons = By.cssSelector("button.ufo-resources-action-bar__primary-button_desktop");
    private By amountOfCheckingFiles = By.className("resources-info-dropdown__name");



    public void downloadFiles() {
        waitForElementVisibility(downloadButton);
        highlightElement(downloadButton);
        driver.findElement(downloadButton).click();
    }


    public void clickDeleteButton(){
        waitForElementVisibility(deleteAndDownloadButtons);
        highlightElement(deleteAndDownloadButtons);
        driver.findElements(deleteAndDownloadButtons).get(0).click();
    }


    public DeleteForeverPopup deleteForever(){
        waitForElementVisibility(deleteForeverButton);
        highlightElement(deleteForeverButton);
        driver.findElement(deleteForeverButton).click();
        return new DeleteForeverPopup();
    }

    public boolean isAmountOfFilesRight(){
        waitForElementVisibility(amountOfCheckingFiles);
        return  driver.findElement(amountOfCheckingFiles).getText().equals("test.json");
    }

}
