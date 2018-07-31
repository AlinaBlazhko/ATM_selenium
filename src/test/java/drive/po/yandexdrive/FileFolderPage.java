package drive.po.yandexdrive;

import drive.po.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FileFolderPage extends AbstractPage {
    private static final By SQUARE_LOCATOR = By.cssSelector("img[src='//2.downloader.disk.yandex." +
            "ru/preview/45fd1140bf2a9ba2c51094a85af311cd5c9e9ea0b5eb8dfbcd0ef3dff5654ab5/inf/h-E5iv20qS6fudmqH3bYJQIJiCgazPYKT2VSCqZE5qCLiq3t7P5xJl8XHUV2wZHhikxqHPRnAj71fyEJ3X37Gg%3D%3D?uid=670590425&filename=%D0%A1%D0%B0%D0%BD%D0%BA%D1%82-%D0%9F%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3.jpg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&tknv=v2&size=80x80']");
    private static final By TARGET_LOCATOR = By.cssSelector("div.listing-item_selected.js-prevent-deselect.js-prevent-deselect");
    private By sortDropDown = By.cssSelector("div.menu__item.menu__item_type_option");

    public FileFolderPage dragNDropSquare() {
        waitForElementVisibility(SQUARE_LOCATOR);
        waitForElementVisibility(TARGET_LOCATOR);
        WebElement element = driver.findElement(SQUARE_LOCATOR);
        WebElement target = driver.findElement(TARGET_LOCATOR);
//        Screenshoter.takeScreenshot();
        new Actions(driver).dragAndDrop(element, target).build().perform();
//        Screenshoter.takeScreenshot();
        System.out.println("Successfully dragged a square!");
        return this;
    }

}
