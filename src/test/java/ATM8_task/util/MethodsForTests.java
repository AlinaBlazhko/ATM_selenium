package ATM8_task.util;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.Callable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by X240 on 8/8/2018.
 */
public class MethodsForTests {
    public static void refreshPage() {
        WebDriverRunner.getWebDriver().navigate().refresh();
    }

    public static void highlight(SelenideElement element) {
        executeJavaScript("arguments[0].style.border='3px solid green'", element);
    }
    public static Callable<Boolean> assertionWaitForTitle(String start) {
        return () -> Selenide.title().toLowerCase().startsWith(start);
    }


    public static void deleteAllEmailsFromFolder() {
        By checkboxes = By.cssSelector("label.nb-checkbox._nb-small-checkbox-checkbox._init");
        By deleteButton = By.xpath("//span[text()='Удалить']");

        ElementsCollection checks = $$(checkboxes);
        if (!$$(checks).isEmpty()) {
            for (WebElement check : checks) {
                check.click();
            }
            $(deleteButton).click();
        }
    }
}
