package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by X240 on 7/22/2018.
 */
public class AbstractPage {
    protected WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_SEC = 15;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementVisibility(By locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SEC)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementPresents(By locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SEC)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
