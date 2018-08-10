package ATM8_task.po.drivepages;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TrashPage{
    private By file = By.cssSelector("div.listing-item__info");
    private By restoreButton = By.cssSelector("button.ufo-resources-action-bar__primary-button_desktop");
    private By cleanTrashButton = By.cssSelector("div.listing-head__additional-actions");

    public void restoreFile(){
        $(file).click();
        $$(restoreButton).get(0).click();
    }

    public void clickToFile(){
        $(file).click();
    }


    public boolean isFileInTrash(){
        return $(file).isDisplayed();
    }

//    public FilesPage goBack(){
//        driver.navigate().back();
//        return new FilesPage();
//    }
//    public void cleanTrash(){
//        waitForElementVisibility(cleanTrashButton);
//        driver.findElement(cleanTrashButton).click();
//    }
}
