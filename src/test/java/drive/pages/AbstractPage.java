package drive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverSingleton;

/**
 * Created by X240 on 7/22/2018.
 */
public class AbstractPage {
    private static final int DEFAULT_TIMEOUT = 25;
    protected WebDriver driver;

    protected AbstractPage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
    }

    protected boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected void waitForElementPresent(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementVisibility(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void highlightElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);
    }

    protected void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }

    private ExpectedCondition<Boolean> isAjaxFinished() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            }
        };
    }

    protected void waitForAjaxProcessed() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(isAjaxFinished());
    }
}
