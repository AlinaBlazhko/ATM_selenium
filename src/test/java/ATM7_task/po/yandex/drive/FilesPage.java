package ATM7_task.po.yandex.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FilesPage extends AbstractPage {
    private static final By SQUARE_LOCATOR = By.cssSelector("div.listing-item__icon-wrapper.js-prevent-mouse-selection");
    private By sortDropDown = By.cssSelector("div.menu__item.menu__item_type_option");
    private By nameOfFolder = By.cssSelector("h1[title='Файлы']");


    public FilesPage dragNDropSquare() {
        waitForElementVisibility(nameOfFolder);
        List<WebElement> element = driver.findElements(SQUARE_LOCATOR);
//        WebElement target = driver.findElement(TARGET_LOCATOR);
//        Screenshoter.takeScreenshot();
        new Actions(driver).clickAndHold(element.get(0)).moveToElement(element.get(1)).click().build().perform();
//        Screenshoter.takeScreenshot();
        System.out.println("Successfully dragged a square!");
        return this;
    }

    public TrashPage openTrash(){
        waitForElementVisibility(SQUARE_LOCATOR);
        highlightElement(SQUARE_LOCATOR);
        new Actions(driver).doubleClick(driver.findElement(SQUARE_LOCATOR)).build().perform();
        return new TrashPage();
    }


    public void chooseSortType(){
        waitForElementVisibility(sortDropDown);
        Select sorts = new Select(driver.findElement(sortDropDown));
        sorts.selectByIndex(2);
    }
}
