package ATM8_task.po.drivepages;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.List;

import static ATM8_task.util.MethodsForTests.highlight;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FilesPage {
    //    private static final By SQUARE_LOCATOR = By.cssSelector("div.listing-item__icon-wrapper.js-prevent-mouse-selection");
    private By file = By.cssSelector("img.js-prevent-deselect.js-prevent-deselect");
    private By files = By.cssSelector("div.listing-item__title.listing-item__title_overflow_clamp");
    private By trashIcon = By.cssSelector("span.js-prevent-deselect");
    private By nameOfFolder = By.cssSelector("h1[title='Файлы']");


    public void dragAndDropSquare() {
        $$(files).get(0).dragAndDropTo($$(files).get(1));
    }

//    public OptionsPopup rightClickToElement(){
//        Actions action = new Actions(driver);
//        action.contextClick(driver.findElements(files).get(0))
//                .sendKeys(Keys.ARROW_DOWN)
//                .sendKeys(Keys.ARROW_DOWN)
//                .sendKeys(Keys.RETURN)
//                .build()
//                .perform();
//        return new OptionsPopup();
//    }
//
    public void openTrash() {
        highlight($(trashIcon));
        $(trashIcon).doubleClick();
//        new Actions(driver).doubleClick(driver.findElement(trashIcon)).build().perform();
//        return new TrashPage();
    }

    public void openContextMenu(){
        $$(files).get(0).contextClick();
    }
//
//
//    public boolean isFileInFileFolder() {
//        waitForElementVisibility(trashIcon);
//        return driver.findElement(file).isDisplayed();
//    }
//
//    public UploadPopup uploadFile() {
//        waitForElementVisibility(trashIcon);
//        driver.findElements(By.cssSelector("input.upload-button__attach")).get(0).
//                sendKeys(new File("src\\test\\resources\\test.json").getAbsolutePath());
//        return new UploadPopup();
//    }
//
//    public ActionBarPopup selectFile(){
//        List<WebElement> listFiles = driver.findElements(files);
//        waitForElementVisibility(files);
//        Actions act=new Actions(driver);
//        act.keyDown(Keys.CONTROL)
//                .click(listFiles.get(0))
//                .keyUp(Keys.CONTROL)
//                .build()
//                .perform();
//        return new ActionBarPopup();
//    }
//
//    public ActionBarPopup clickToFirstFileFromList(){
//        waitForElementVisibility(files);
//        driver.findElements(files).get(1).click();
//        return new ActionBarPopup();
//    }
//
//    public ActionBarPopup clickTrashButton(){
//        waitForElementVisibility(trashIcon);
//        driver.findElement(trashIcon).click();
//        return new ActionBarPopup();
//    }
}
