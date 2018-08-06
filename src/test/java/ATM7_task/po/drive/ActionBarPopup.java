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
    private By downloadButton = By.cssSelector("button.ufo-resources-action-bar__primary-button_desktop");
    private By deleteButton = By.cssSelector("button.groupable-buttons__visible-button.groupable-buttons__visible-button_name_delete ");
    private By amountOfCheckingFiles = By.className("selection-info__text");



    public void downloadFiles(){
        waitForElementVisibility(downloadButton);
        highlightElement(downloadButton);
        driver.findElement(downloadButton).click();
    }


    public void clickDeleteButton(){
        waitForElementVisibility(deleteButton);
        highlightElement(deleteButton);
        driver.findElement(deleteButton).click();
    }


    public DeleteForeverPopup deleteForever(){
        waitForElementVisibility(deleteForeverButton);
        highlightElement(deleteForeverButton);
        driver.findElement(deleteForeverButton).click();
        return new DeleteForeverPopup();
    }

    public boolean isAmountOfFilesRight(){
        waitForElementVisibility(amountOfCheckingFiles);
        return  driver.findElement(amountOfCheckingFiles).getText().endsWith("2 файла");
    }

}
