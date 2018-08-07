package ATM7_task.po.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.security.Key;
import java.util.List;

public class FilesPage extends AbstractPage {
    //    private static final By SQUARE_LOCATOR = By.cssSelector("div.listing-item__icon-wrapper.js-prevent-mouse-selection");
    private By file = By.cssSelector("img.js-prevent-deselect.js-prevent-deselect");
    private By files = By.cssSelector("div.listing-item__title.listing-item__title_overflow_clamp");
    private By trashIcon = By.cssSelector("span.js-prevent-deselect");
    private By nameOfFolder = By.cssSelector("h1[title='Файлы']");


    public FilesPage dragAndDropSquare() {
        waitForElementVisibility(nameOfFolder);
        new Actions(driver).clickAndHold(driver.findElements(files).get(0)).
                moveToElement(driver.findElement(trashIcon)).click().build().perform();
        return this;
    }

    public OptionsPopup rightClickToElement(){
        Actions action = new Actions(driver);
        action.contextClick(driver.findElements(files).get(0))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();
        return new OptionsPopup();
    }

    public TrashPage openTrash() {
        waitForElementVisibility(trashIcon);
        highlightElement(trashIcon);
        new Actions(driver).doubleClick(driver.findElement(trashIcon)).build().perform();
        return new TrashPage();
    }


    public boolean isFileInFileFolder() {
        waitForElementVisibility(trashIcon);
        return driver.findElement(file).isDisplayed();
    }

    public UploadPopup uploadFile() {
        waitForElementVisibility(trashIcon);
        driver.findElements(By.cssSelector("input.upload-button__attach")).get(0).
                sendKeys(new File("src\\test\\resources\\test.json").getAbsolutePath());
        return new UploadPopup();
    }

    public ActionBarPopup selectFile(){
        List<WebElement> listFiles = driver.findElements(files);
        waitForElementVisibility(files);
        Actions act=new Actions(driver);
        act.keyDown(Keys.CONTROL)
                .click(listFiles.get(0))
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
        return new ActionBarPopup();
    }

    public ActionBarPopup clickToFirstFileFromList(){
        waitForElementVisibility(files);
        driver.findElements(files).get(1).click();
        return new ActionBarPopup();
    }

    public ActionBarPopup clickTrashButton(){
        waitForElementVisibility(trashIcon);
        driver.findElement(trashIcon).click();
        return new ActionBarPopup();
    }
}
